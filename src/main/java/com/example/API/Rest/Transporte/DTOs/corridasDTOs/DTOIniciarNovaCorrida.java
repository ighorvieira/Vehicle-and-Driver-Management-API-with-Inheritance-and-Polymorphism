package com.example.API.Rest.Transporte.DTOs.corridasDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOIniciarNovaCorrida {
    private String origem;
    private String destino;
    private double distancia;
    private String empresa;
    private Data data;
}
