package br.com.fiap.javaGlobalSolution.dto.response.pessoa;

import lombok.Builder;

@Builder
public record TelefoneResponse(

        Long id,

        String ddi,

        String ddd,

        String numero,

        PessoaResponse pessoa
) {
}
