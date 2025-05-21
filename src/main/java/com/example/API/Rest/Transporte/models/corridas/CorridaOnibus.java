package com.example.API.Rest.Transporte.models.corridas;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.API.Rest.Transporte.dtos.corridasdtos.Data;
import com.example.API.Rest.Transporte.models.peoples.Motorista;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CorridaOnibus extends Corrida {

    private final double EMPRESA_X_ECONOMICA = 50.00;
    private final double EMPRESA_X_CONFORT = 100.00;
    private final double EMPRESA_X_EXECUTIVO = 150.00;
    private final double EMPRESA_Y_ECONOMICA = 70.00;
    private final double EMPRESA_Y_CONFORT = 145.00;
    private final double EMPRESA_Y_EXECUTIVO = 170.00;

    private String empresa;

    public CorridaOnibus() {

    }

    public CorridaOnibus(String origem, String destino, double distancia, double valor, boolean finalizou, Data data, Motorista motorista, String empresa) {
        super(origem, destino, distancia, valor, finalizou, data, motorista);
        this.empresa = empresa;
    }

    @Override
    public double calcularValor() {
        switch (empresa) {
            case "EMPRESA_X_ECONOMICA":
                setValor(EMPRESA_X_ECONOMICA);
                return getValor();
                
            case "EMPRESA_X_CONFORT":
                setValor(EMPRESA_X_CONFORT);
                return getValor();

            case "EMPRESA_X_EXECUTIVO":
                setValor(EMPRESA_X_EXECUTIVO);
                return getValor();

            case "EMPRESA_Y_ECONOMICA":
                setValor(EMPRESA_X_ECONOMICA);
                return getValor();
            
            case "EMPRESA_Y_CONFORT":
                setValor(EMPRESA_Y_CONFORT);
                return getValor();

            case "EMPRESA_Y_EXECUTIVO":
                setValor(EMPRESA_X_EXECUTIVO);
                return getValor();
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passagem não encontrada");
        }
    }

    
}
