package com.example.API.Rest.Transporte.models.corridas;

import com.example.API.Rest.Transporte.dtos.corridasdtos.Data;
import com.example.API.Rest.Transporte.models.peoples.Motorista;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CorridasCarros extends Corrida {

    private final double VALOR_POR_KM = 2.00;

    @Override
    public double calcularValor() {
        this.setValor(getDistancia() * VALOR_POR_KM);
        return VALOR_POR_KM;
    }

    public CorridasCarros() {
        
    }

    public CorridasCarros(String origem, String destino, double distancia, double valor, boolean finalizou, Data data, Motorista motorista) {
        super(origem, destino, distancia, valor, finalizou, data, motorista);
    }
}
