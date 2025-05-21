package com.example.API.Rest.Transporte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.API.Rest.Transporte.dtos.corridasdtos.DTOIniciarNovaCorrida;
import com.example.API.Rest.Transporte.dtos.corridasdtos.DTORetornaCorrida;
import com.example.API.Rest.Transporte.models.corridas.CorridaOnibus;
import com.example.API.Rest.Transporte.models.peoples.Motorista;
import com.example.API.Rest.Transporte.repository.RepositoryCorridaOnibus;
import com.example.API.Rest.Transporte.repository.RepositoryMotorista;

@Service
public class ServiceCorridaOnibus {

    @Autowired
    RepositoryMotorista repositoryMotorista;
    
    @Autowired
    RepositoryCorridaOnibus repositoryCorridaOnibus;

    @Autowired
    SeriviceMotorista seriviceMotorista;

    public ResponseEntity<?> pegarCorridas() {
        return ResponseEntity.ok(repositoryCorridaOnibus.findAll());
    }

    public DTORetornaCorrida iniciarNovaCorrida(DTOIniciarNovaCorrida novaCorrida) {
        Motorista motorista = seriviceMotorista.pegarUmMotorista("Onibus");
        motorista.setDisponivel(false);

        CorridaOnibus corridaOnibus = new CorridaOnibus(
            novaCorrida.getOrigem(),
            novaCorrida.getDestino(),
            novaCorrida.getDistancia(),
            0,
            false,
            novaCorrida.getData(),
            motorista, //injetar motorista
            novaCorrida.getEmpresa());

        corridaOnibus.calcularValor();

        repositoryCorridaOnibus.save(corridaOnibus);

        return new DTORetornaCorrida(
            corridaOnibus.getOrigem(), 
            corridaOnibus.getDestino(), 
            corridaOnibus.getDistancia(), 
            corridaOnibus.getValor(), 
            motorista.getNome(),
            motorista.getEmail(),
            motorista.getTipoDeClassificação(),
            corridaOnibus.getData(), 
            motorista.getCarro());
    }

    public ResponseEntity<?> finalizarCorrida(Long id) {
        CorridaOnibus corridaOnibus = repositoryCorridaOnibus.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ainda não há corridas cadastradas"));

        corridaOnibus.getMotorista().setDisponivel(true);

        corridaOnibus.setFinalizou(true);

        repositoryCorridaOnibus.save(corridaOnibus);
        repositoryMotorista.save(corridaOnibus.getMotorista());

        return ResponseEntity.ok("Corrida finalizada com sucesso");
    }
}
