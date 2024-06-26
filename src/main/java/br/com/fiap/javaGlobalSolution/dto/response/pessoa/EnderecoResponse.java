package br.com.fiap.javaGlobalSolution.dto.response.pessoa;

import lombok.Builder;

@Builder
public record EnderecoResponse(

        Long id,

        String cep,

        String logradouro,

        String numero,

        String complemento,

        String cidade,

        String estado,

        PessoaResponse pessoa
) {
}
