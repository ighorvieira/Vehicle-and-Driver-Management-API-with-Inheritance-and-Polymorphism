package com.example.API.Rest.Transporte.DTOs.motoristasDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOCadastrarMotoristas {
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private String tipoDeVeiculo;
    private boolean disponivel;
    private Carro carro;
}
