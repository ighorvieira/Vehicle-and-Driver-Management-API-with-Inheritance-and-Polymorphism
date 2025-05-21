package com.example.API.Rest.Transporte.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API.Rest.Transporte.dtos.corridasdtos.DTOIniciarNovaCorrida;
import com.example.API.Rest.Transporte.dtos.corridasdtos.DTORetornaCorrida;
import com.example.API.Rest.Transporte.service.ServiceCorridaCarro;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/corridas")
public class ControllerCorridaCarro {
    
    @Autowired
    ServiceCorridaCarro serviceCorridaCarro;

    @Operation(tags = "Corrida Carros", summary = "Lista Corridas Carro", description = "Lista todas as corridas de carros")
    @GetMapping("/carros")
    public ResponseEntity<?> pegarCorridasCarro() {
        return serviceCorridaCarro.pegarCorridas();
    }

    @Operation(tags = "Corrida Carros", summary = "Inicia Corrida Carro", description = "Inicia Uma nova corrida de carro")
    @PostMapping("/carros")
    public DTORetornaCorrida iniciarNovaCorrida(@RequestBody DTOIniciarNovaCorrida corrida) {
        return serviceCorridaCarro.iniciarNovaCorridaCarro(corrida);
    }

    @Operation(tags = "Corrida Carros", summary = "Finaliza Corrida Carro", description = "Muda o status da corrida e do motorista")
    @PutMapping("/carros/{id}/status")
    public ResponseEntity<?> finalizarCorrida(@PathVariable Long id) {
        return serviceCorridaCarro.finalizarCorrida(id);
    }
}
