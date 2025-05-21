package com.example.API.Rest.Transporte.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API.Rest.Transporte.DTOs.corridasDTOs.DTOIniciarNovaCorrida;
import com.example.API.Rest.Transporte.DTOs.corridasDTOs.DTORetornaCorrida;
import com.example.API.Rest.Transporte.Service.ServiceCorridaOnibus;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/corrida")
public class ControllerCorridaOnibus {
    
    @Autowired
    ServiceCorridaOnibus serviceCorridaOnibus;
    
    @Operation(tags = "Corrida Onibus", summary = "Lista Corridas Onibus", description = "Lista todas as corridas de onibus")
    @GetMapping("/onibus")
    public ResponseEntity<?> pegarCorridas() {
        return serviceCorridaOnibus.pegarCorridas();
    }

    @Operation(tags = "Corrida Onibus", summary = "Inicia Corrida Onibus", description = "Inicia uma nova corrida de onibus")
    @PostMapping("/onibus")
    public DTORetornaCorrida iniciarUmaNovaCorrida(@RequestBody DTOIniciarNovaCorrida corrida) {
        return serviceCorridaOnibus.iniciarNovaCorrida(corrida);
    }

    @Operation(tags = "Corrida Onibus", summary = "FInaliza Corrida Onibus", description = "Muda o status da corrida e do motorista")
    @PutMapping("/onibus/{id}/status")
    public ResponseEntity<?> finalizarCorrida(@PathVariable Long id) {
        return serviceCorridaOnibus.finalizarCorrida(id);
    }
}
