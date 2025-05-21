package com.example.API.Rest.Transporte.dtos.corridasdtos;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Data {
    private int dia;
    private int mes;
    private int ano;
}
