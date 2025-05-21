package com.example.API.Rest.Transporte.dtos.motoristasdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOAtualizarMotorista {
    private String nome;
    private String telefone;
    private String email;
    private boolean disponivel;
    private String tipoDeVeiculo;
    private Carro carro;

    public DTOAtualizarMotorista(){

    }

    public DTOAtualizarMotorista(String nome, String telefone, String email, boolean disponivel, String tipoDeVeiculo, Carro carro) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.disponivel = disponivel;
        this.tipoDeVeiculo = tipoDeVeiculo;
        this.carro = carro;
    }
}
