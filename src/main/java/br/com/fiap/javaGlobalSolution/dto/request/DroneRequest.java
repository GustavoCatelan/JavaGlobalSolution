package br.com.fiap.javaGlobalSolution.dto.request;

import java.time.LocalDate;

public record DroneRequest (

        String modelo,

        String marca,

        String distanciaMaxima,

        String duracaoBateria,

        LocalDate dataFabricacao,

        AbstractRequest fabricante
){
}