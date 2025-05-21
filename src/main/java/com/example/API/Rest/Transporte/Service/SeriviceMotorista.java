package com.example.API.Rest.Transporte.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.API.Rest.Transporte.dtos.motoristasDTOs.DTOAtualizarMotorista;
import com.example.API.Rest.Transporte.dtos.motoristasDTOs.DTOCadastrarMotoristas;
import com.example.API.Rest.Transporte.dtos.motoristasDTOs.DTOProcuraMotoristaEmailESenha;
import com.example.API.Rest.Transporte.dtos.motoristasDTOs.DTOReturnMotorista;
import com.example.API.Rest.Transporte.models.peoples.Motorista;
import com.example.API.Rest.Transporte.repository.RepositoryMotorista;

@Service
public class SeriviceMotorista {
    
    @Autowired
    RepositoryMotorista repositoryMotorista;

    //tentar passar uma camada de segurança 
    public ResponseEntity<List<Motorista>> pegarMotoristas() {
        return ResponseEntity.ok(repositoryMotorista.findAll());
    }

    public ResponseEntity<?> cadastrarMotorista(DTOCadastrarMotoristas motorista) {
        if (motorista.getNome().isEmpty() || motorista.getTelefone().isEmpty() || 
        motorista.getEmail().isEmpty() || motorista.getSenha().isEmpty() || motorista.isDisponivel() != true ||
        motorista.getCarro().getMarca().isEmpty() || motorista.getCarro().getAno() < 1 ||
        motorista.getCarro().getModelo().isEmpty() || motorista.getCarro().getPlaca().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os campos não podem ser vazios");
        }

        if (motorista.getCarro().getAno() < 2010) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ano do seu carro deve ser a partir de 2010");
        }

        if (repositoryMotorista.findByEmailAndSenha(motorista.getEmail().toLowerCase(), motorista.getSenha().toLowerCase()).isPresent() ||
            repositoryMotorista.findByEmail(motorista.getEmail().toLowerCase()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuario cadastrado com esse email");
        }        

        if (!motorista.getTipoDeVeiculo().equalsIgnoreCase("onibus") && 
            !motorista.getTipoDeVeiculo().equalsIgnoreCase("carro")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os veiculos para cadastro são onibus ou carro");
        }

        Motorista novoMotorista = new Motorista(motorista.getNome(), motorista.getTelefone(), motorista.getEmail().toLowerCase(), motorista.getSenha().toLowerCase(), motorista.isDisponivel(), motorista.getTipoDeVeiculo().toLowerCase(), motorista.getCarro());
        novoMotorista.classificacaoMotorista();
        repositoryMotorista.save(novoMotorista);
        return ResponseEntity.created(null).body(new DTOReturnMotorista(
            novoMotorista.getNome(),
            novoMotorista.getTelefone(),
            novoMotorista.getTipoDeVeiculo(), 
            novoMotorista.getTipoDeClassificação(), 
            novoMotorista.isDisponivel(), 
            novoMotorista.getCarro()));
    }

    public ResponseEntity<?> pegarMotoristasDistoniveis() {
        List<Motorista> motoristas = repositoryMotorista.findByDisponibilidadeDeMotoristas();
        ArrayList<DTOReturnMotorista> lista = new ArrayList<>();
        for (int i = 0; i < motoristas.size(); i++) {
            Motorista motorista = motoristas.get(i);
            lista.add(new DTOReturnMotorista(
                motorista.getNome(),
                motorista.getTelefone(),
                motorista.getTipoDeVeiculo(),
                motorista.getTipoDeClassificação(),
                motorista.isDisponivel(),
                motorista.getCarro()));
        }
        return ResponseEntity.ok(lista);
    }

    public ResponseEntity<?> procurarMotoristaPorEmailESenha(DTOProcuraMotoristaEmailESenha login) {
        Motorista motorista = repositoryMotorista.findByEmailAndSenha(login.getEmail().toLowerCase(), login.getSenha().toLowerCase())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
        
        return ResponseEntity.ok(new DTOReturnMotorista(
            motorista.getNome(),
            motorista.getTelefone(),
            motorista.getTipoDeVeiculo(),
            motorista.getTipoDeClassificação(),
            motorista.isDisponivel(),
            motorista.getCarro()));
    }

    public List<DTOReturnMotorista> pegarMotoristasDisponiveisPorVeiculo(String tipoVeiculo) {
        if (tipoVeiculo.equalsIgnoreCase("Onibus")) {
            List<Motorista> motoristasOnibus = repositoryMotorista.findByMotoristasOnibus()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há motoristas de onibus cadastrados"));
            ArrayList<DTOReturnMotorista> listaMotoristasOnibus = new ArrayList<>();
            for (int i = 0; i < motoristasOnibus.size(); i++) {
                Motorista motorista = motoristasOnibus.get(i);
                listaMotoristasOnibus.add(new DTOReturnMotorista(
                    motorista.getNome(),
                    motorista.getTelefone(),
                    motorista.getTipoDeVeiculo(),
                    motorista.getTipoDeClassificação(),
                    motorista.isDisponivel(),
                    motorista.getCarro()));
            }
            return listaMotoristasOnibus;

        } else if (tipoVeiculo.equalsIgnoreCase("Carro")) {
            List<Motorista> motoristasCarro = repositoryMotorista.findByMotoristasCarro()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há motoristas de carro cadastrados"));
            ArrayList<DTOReturnMotorista> listaMotoristasCarro = new ArrayList<>();
            for (int i = 0; i < motoristasCarro.size(); i++) {
                Motorista motorista = motoristasCarro.get(i);
                listaMotoristasCarro.add(new DTOReturnMotorista(
                    motorista.getNome(),
                    motorista.getTelefone(),
                    motorista.getTipoDeVeiculo(),
                    motorista.getTipoDeClassificação(),
                    motorista.isDisponivel(),
                    motorista.getCarro()));
            }
            return listaMotoristasCarro;

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O veiculo deve ser onibus ou carro");
        }
    }

    public ResponseEntity<?> atualizarDadosMotoristas(DTOAtualizarMotorista dadosMotorista) {
        Motorista motorista = repositoryMotorista.findByEmail(dadosMotorista.getEmail())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado"));

        if (!motorista.getTipoDeVeiculo().equalsIgnoreCase("onibus") && 
            !motorista.getTipoDeVeiculo().equalsIgnoreCase("carro")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os veiculos para cadastro são onibus ou carro");
        }

        if (motorista.getCarro().getAno() < 2010) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ano do seu carro deve ser a partir de 2010");
        }

        motorista.setNome(dadosMotorista.getNome());
        motorista.setTelefone(dadosMotorista.getTelefone());
        motorista.setEmail(dadosMotorista.getEmail());
        motorista.setTipoDeVeiculo(dadosMotorista.getTipoDeVeiculo());
        motorista.setDisponivel(dadosMotorista.isDisponivel());
        motorista.setCarro(dadosMotorista.getCarro());

        repositoryMotorista.save(motorista);

        return ResponseEntity.ok(new DTOReturnMotorista(
            motorista.getNome(),
            motorista.getTelefone(),
            motorista.getTipoDeVeiculo(),
            motorista.getTipoDeClassificação(),
            motorista.isDisponivel(),
            motorista.getCarro())); 
    }

    public ResponseEntity<?> deletarMotorista(String email) {
        Motorista motorista = repositoryMotorista.findByEmail(email.toLowerCase())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado"));

        repositoryMotorista.delete(motorista);

        return ResponseEntity.ok("Motorista deletado com sucesso");
    }

    public Motorista pegarUmMotorista(String tipoVeiculo) {
        if (tipoVeiculo.equalsIgnoreCase("Onibus")) {
            List<Motorista> motoristasOnibus = repositoryMotorista.findByMotoristasOnibus()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum motorista de onibus encontrado"));
            Motorista motorista = motoristasOnibus.get(0);
            return motorista;
        } else if (tipoVeiculo.equalsIgnoreCase("Carro")) {
            List<Motorista> motoristasCarro = repositoryMotorista.findByMotoristasCarro()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum motorista de onibus encontrado"));
            Motorista motorista = motoristasCarro.get(0);
            return motorista;
        }
        return null;
    }
}
