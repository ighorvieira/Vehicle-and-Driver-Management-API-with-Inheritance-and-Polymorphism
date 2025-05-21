package com.example.API.Rest.Transporte.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API.Rest.Transporte.dtos.motoristasdtos.DTOAtualizarMotorista;
import com.example.API.Rest.Transporte.dtos.motoristasdtos.DTOCadastrarMotoristas;
import com.example.API.Rest.Transporte.dtos.motoristasdtos.DTOProcuraMotoristaEmailESenha;
import com.example.API.Rest.Transporte.dtos.motoristasdtos.DTOReturnMotorista;
import com.example.API.Rest.Transporte.service.SeriviceMotorista;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/motoristas")
public class ControllerMotorista {

    //http://localhost:8080/h2-console
    //http://localhost:8080/swagger-ui/index.html
    
    @Autowired
    SeriviceMotorista seriviceMotorista;

    @Operation(tags = "Motoristas", summary = "Listar Motoristas", description = "Lista todos os motoristas")
    @GetMapping
    public ResponseEntity<?> pegarMotoristas() {
        return seriviceMotorista.pegarMotoristas();
    }

    @Operation(tags = "Motoristas", summary = "Cadastrar Motorista", description = "Cadastra um novo motorista")
    @PostMapping()
    public ResponseEntity<?> cadastraMotorista(@RequestBody DTOCadastrarMotoristas motorista) {
        return seriviceMotorista.cadastrarMotorista(motorista);
    }

    @Operation(tags = "Motoristas", summary = "Lista Motoristas Disponiveis", description = "Lista todos os motoristas que estão como status disponiveis")
    @GetMapping("/disponiveis")
    public ResponseEntity<?> pegarMotoristasDisponiveis() {
        return seriviceMotorista.pegarMotoristasDistoniveis();
    }

    @Operation(tags = "Motoristas", summary = "Busca Motorista", description = "Faz a busca de um motorista por email e senha")
    @PostMapping("/login")
    public ResponseEntity<?> pegarMotoristaPorLogin(@RequestBody DTOProcuraMotoristaEmailESenha loginMotorista) {
        return seriviceMotorista.procurarMotoristaPorEmailESenha(loginMotorista);
    }

    @Operation(tags = "Motoristas", summary = "Lista Motoristas Onibus", description = "Faz a busca e lista um DTO comm todos os motoristas de onibus")
    @GetMapping("/onibus")
    public List<DTOReturnMotorista> pegarMotoristasOnibus(String tipoVeiculo) {
        return seriviceMotorista.pegarMotoristasDisponiveisPorVeiculo(tipoVeiculo);
    }

    @Operation(tags = "Motoristas", summary = "Atualiza Motorista", description = "Atualiza alguns dos dados do motorista")
    @PutMapping
    public ResponseEntity<?> atualizarDadosMotorista(@RequestBody DTOAtualizarMotorista motorista) {
        return seriviceMotorista.atualizarDadosMotoristas(motorista);
    }

    @Operation(tags = "Motoristas", summary = "Deleta Motorista", description = "Deleta um motorista pegando como parametro o email")
    @DeleteMapping("/motoristas/{email}")
    public ResponseEntity<?> deletarMotoristas(@PathVariable String email) {
        return seriviceMotorista.deletarMotorista(email);
    }
}
