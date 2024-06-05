package br.com.fiap.javaGlobalSolution.dto.response.pessoa;

import lombok.Builder;

@Builder
public record PessoaResponse(

        Long id,

        String nome
) {
}
