package com.example.API.Rest.Transporte.dtos.motoristasdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOReturnMotorista {
    private String nome;
    private String telefone;
    private String veiculo;
    private String classificacao;
    private boolean disponivel;
    private Carro carro;

    public DTOReturnMotorista(String nome, String telefone, String veiculo, String classificacao, boolean disponivel, Carro carro) {
        this.nome = nome;
        this.telefone = telefone;
        this.veiculo = veiculo;
        this.classificacao = classificacao;
        this.disponivel = disponivel;
        this.carro = carro;
    }
}
