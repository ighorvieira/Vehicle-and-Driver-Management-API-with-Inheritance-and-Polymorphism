package com.example.API.Rest.Transporte.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.API.Rest.Transporte.dtos.corridasDTOs.DTOIniciarNovaCorrida;
import com.example.API.Rest.Transporte.dtos.corridasDTOs.DTORetornaCorrida;
import com.example.API.Rest.Transporte.models.corridas.CorridasCarros;
import com.example.API.Rest.Transporte.models.peoples.Motorista;
import com.example.API.Rest.Transporte.repository.RepositoryCorridaCarro;
import com.example.API.Rest.Transporte.repository.RepositoryMotorista;

@Service
public class ServiceCorridaCarro {
    
    @Autowired
    RepositoryCorridaCarro repositoryCorridaCarro;

    @Autowired
    SeriviceMotorista seriviceMotorista;

    @Autowired
    RepositoryMotorista repositoryMotorista;

    public ResponseEntity<?> pegarCorridas() {
        return ResponseEntity.ok(repositoryCorridaCarro.findAll());
    }

    public DTORetornaCorrida iniciarNovaCorridaCarro(DTOIniciarNovaCorrida novaCorrida) {
        Motorista motorista = seriviceMotorista.pegarUmMotorista("Carro");
        motorista.setDisponivel(false);

        CorridasCarros corridaCarro = new CorridasCarros(
            novaCorrida.getOrigem(),
            novaCorrida.getDestino(),
            novaCorrida.getDistancia(),
            0,
            false,
            novaCorrida.getData(),
            motorista);

        corridaCarro.calcularValor();

        repositoryCorridaCarro.save(corridaCarro);

        return new DTORetornaCorrida(
            corridaCarro.getOrigem(), 
            corridaCarro.getDestino(), 
            corridaCarro.getDistancia(), 
            corridaCarro.getValor(), 
            motorista.getNome(),
            motorista.getEmail(),
            motorista.getTipoDeClassificação(),
            corridaCarro.getData(), 
            motorista.getCarro());
    }

    public ResponseEntity<?> finalizarCorrida(Long id) {
        CorridasCarros corridasCarros = repositoryCorridaCarro.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ainda não há corridas cadastradas"));

        corridasCarros.getMotorista().setDisponivel(true);

        corridasCarros.setFinalizou(true);

        repositoryCorridaCarro.save(corridasCarros);
        repositoryMotorista.save(corridasCarros.getMotorista());

        return ResponseEntity.ok("Corrida finalizada com sucesso");
    }
}