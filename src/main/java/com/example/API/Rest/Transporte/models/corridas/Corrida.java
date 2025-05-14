package com.example.API.Rest.Transporte.models.corridas;

import com.example.API.Rest.Transporte.DTOs.corridasDTOs.Data;
import com.example.API.Rest.Transporte.models.peoples.Motorista;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GenerationType;

@Getter
@Setter
@MappedSuperclass
public abstract class Corrida {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String origem;
    private String destino;
    private double distancia;
    private double valor;
    private boolean finalizou;
    private Data data;

    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;

    public abstract double calcularValor();

    public Corrida() {

    }

    public Corrida(String origem, String destino, double distancia, double valor, boolean finalizou, Data data, Motorista motorista) {
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
        this.valor = valor;
        this.finalizou = finalizou;
        this.data = data;
        this.motorista = motorista;
    }
}
