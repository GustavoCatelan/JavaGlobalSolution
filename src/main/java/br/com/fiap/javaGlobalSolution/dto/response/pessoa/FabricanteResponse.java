package br.com.fiap.javaGlobalSolution.dto.response.pessoa;

import lombok.Builder;

@Builder
public record FabricanteResponse(

        Long id,

        String nome,

        String email,

        String cnpj,

        String setor,

        PessoaResponse pessoa
) {
}
