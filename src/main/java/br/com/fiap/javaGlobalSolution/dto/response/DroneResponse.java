package br.com.fiap.javaGlobalSolution.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DroneResponse (

        Long id,

        String modelo,

        String marca,

        String distanciaMaxima,

        String duracaoBateria,

        LocalDate dataFabricacao,

        FabricanteResponse fabricante
){
}
