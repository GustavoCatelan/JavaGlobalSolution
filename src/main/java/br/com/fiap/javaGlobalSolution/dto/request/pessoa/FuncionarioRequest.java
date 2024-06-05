package br.com.fiap.javaGlobalSolution.dto.request.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.AbstractRequest;

public record FuncionarioRequest (

        String nome,

        String funcao,

        String cpf,

        String email,

        AbstractRequest pessoa
){
}
