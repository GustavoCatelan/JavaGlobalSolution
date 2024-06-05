package br.com.fiap.javaGlobalSolution.dto.response.maquina;

import lombok.Builder;

@Builder
public record BarcoDeRecargaResponse(

        Long id,

        String modelo,

        String quantidadeFuncionario,

        String velocidadeMaxima,

        String sistemaNavegacao,

        String velocidadeRecarga
) {
}
