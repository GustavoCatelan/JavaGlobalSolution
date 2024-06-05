package br.com.fiap.javaGlobalSolution.dto.response.maquina;

import lombok.Builder;

@Builder
public record CoordenadaResponse(

        Long id,

        Double longitude,

        Double latitude,

        Double altitude,

        MaquinaResponse maquina
) {
}
