package br.com.fiap.javaGlobalSolution.dto.request.maquina;

import br.com.fiap.javaGlobalSolution.dto.request.AbstractRequest;

public record CoordenadaRequest (

        Double longitude,

        Double latitude,

        Double altitude,

        AbstractRequest maquina
){
}
