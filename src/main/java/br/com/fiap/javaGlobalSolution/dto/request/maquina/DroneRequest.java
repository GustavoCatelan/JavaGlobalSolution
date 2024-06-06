package br.com.fiap.javaGlobalSolution.dto.request.maquina;

import br.com.fiap.javaGlobalSolution.dto.request.AbstractRequest;

import java.time.LocalDate;

public record DroneRequest (

        String distanciaMaxima,

        String duracaoBateria,

        LocalDate dataFabricacao,

        AbstractRequest fabricante
){
}
