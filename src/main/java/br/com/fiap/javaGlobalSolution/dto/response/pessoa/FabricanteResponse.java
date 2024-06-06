package br.com.fiap.javaGlobalSolution.dto.response.pessoa;

import lombok.Builder;

@Builder
public record FabricanteResponse(

        Long id,

        String email,

        String setor,

        PessoaResponse pessoa
) {
}
