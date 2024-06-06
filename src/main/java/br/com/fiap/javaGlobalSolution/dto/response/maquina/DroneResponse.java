package br.com.fiap.javaGlobalSolution.dto.response.maquina;

import br.com.fiap.javaGlobalSolution.dto.response.pessoa.FabricanteResponse;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DroneResponse (

        Long id,

        String distanciaMaxima,

        String duracaoBateria,

        LocalDate dataFabricacao,

        FabricanteResponse fabricante
){
}
