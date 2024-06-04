package br.com.fiap.javaGlobalSolution.dto.request;

public record CoordenadaRequest (

        Double longitude,

        Double latitude,

        Double altitude,

        DroneRequest drone,

        BarcoDeRecargaRequest barcoDeRecarga
){
}
