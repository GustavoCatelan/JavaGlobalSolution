package br.com.fiap.javaGlobalSolution.dto.request;

public record CoordenadaRequest (

        Double longitude,

        Double latitude,

        Double altitude,

        AbstractRequest drone,

        AbstractRequest barcoDeRecarga
){
}
