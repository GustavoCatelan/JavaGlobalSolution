package br.com.fiap.javaGlobalSolution.dto.response;

import lombok.Builder;

@Builder
public record CoordenadaResponse(

        Long id,

        Double longitude,

        Double latitude,

        Double altitude,

        DroneResponse drone,

        BarcoDeRecargaResponse barcoDeRecarga
) {
}
