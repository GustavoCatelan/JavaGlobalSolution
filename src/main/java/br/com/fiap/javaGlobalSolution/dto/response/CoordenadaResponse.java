package br.com.fiap.javaGlobalSolution.dto.response;

import lombok.Builder;
import org.springframework.lang.Nullable;

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
