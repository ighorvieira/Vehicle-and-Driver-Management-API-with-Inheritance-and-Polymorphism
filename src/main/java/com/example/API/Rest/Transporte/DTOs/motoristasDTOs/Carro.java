package com.example.API.Rest.Transporte.DTOs.motoristasDTOs;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Carro {
    private String modelo;
    private String marca;
    private String placa;
    private int ano;
}
