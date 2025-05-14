package com.example.API.Rest.Transporte.models.peoples;

import com.example.API.Rest.Transporte.DTOs.motoristasDTOs.Carro;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "motoristas")
@Getter
@Setter
public class Motorista extends Pessoa {

    private boolean disponivel;
    private String tipoDeClassificação;
    private String tipoDeVeiculo;
    private Carro carro;

    public Motorista() {

    }

    public Motorista(String nome, String telefone, String email, String senha, boolean disponivel, String tipoDeVeiculo, Carro carro) {
        super(nome, telefone, email, senha);
        this.disponivel = disponivel;
        this.tipoDeVeiculo = tipoDeVeiculo;
        this.carro = carro;
    }

    public void classificacaoMotorista() {
        int anoCarro = this.getCarro().getAno();
        if (anoCarro >= 2010 && anoCarro < 2015) {
            this.tipoDeClassificação = "Economica";
        } else if (anoCarro >= 2015 && anoCarro < 2020) {
            this.tipoDeClassificação = "Confort";
        } else if (anoCarro >= 2020 && anoCarro < 2026) {
            this.tipoDeClassificação = "Executivo";
        }
    }
}
