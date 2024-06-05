package br.com.fiap.javaGlobalSolution.dto.request.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.AbstractRequest;

public record EnderecoRequest (

        String cep,

        String logradouro,

        String numero,

        String complemento,

        String cidade,

        String estado,

        AbstractRequest pessoa
){
}
