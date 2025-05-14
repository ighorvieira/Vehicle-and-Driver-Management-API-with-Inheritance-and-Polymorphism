package com.example.API.Rest.Transporte.DTOs.corridasDTOs;

import com.example.API.Rest.Transporte.DTOs.motoristasDTOs.Carro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTORetornaCorrida {
    private String origem;
    private String destino;
    private double distancia;
    private double valor;
    private String nomeMotorista;
    private String email;
    private String tipoDeClassificação;
    private Data data;
    private Carro carro;

    public DTORetornaCorrida(String origem, String destino, double distancia, double valor,
    String nomeMotorista, String email, String tipoDeClassificação, Data data, Carro carro) {
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
        this.valor = valor;
        this.nomeMotorista = nomeMotorista;
        this.email = email;
        this.tipoDeClassificação = tipoDeClassificação;
        this.data = data;
        this.carro = carro;
    }
}
