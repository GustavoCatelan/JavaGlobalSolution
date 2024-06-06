package br.com.fiap.javaGlobalSolution.dto.response.maquina;

import lombok.Builder;

@Builder
public record BarcoDeRecargaResponse(

        Long id,

        String quantidadeFuncionario,

        String velocidadeMaxima,

        String sistemaNavegacao,

        String velocidadeRecarga
) {
}
