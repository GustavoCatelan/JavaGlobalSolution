package br.com.fiap.javaGlobalSolution.dto.response.maquina;

import lombok.Builder;

@Builder
public record MaquinaResponse(

        Long id,

        String modelo,

        String marca
) {
}
