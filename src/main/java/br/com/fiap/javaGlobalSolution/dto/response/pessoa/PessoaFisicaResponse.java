package br.com.fiap.javaGlobalSolution.dto.response.pessoa;

import lombok.Builder;

@Builder
public record PessoaFisicaResponse(

        Long id,

        String cpf,

        String rg
) {
}
