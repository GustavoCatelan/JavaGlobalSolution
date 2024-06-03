package br.com.fiap.javaGlobalSolution.dto.request;

public record FuncionarioRequest (

        String nome,

        String funcao,

        String cpf,

        String email
){
}
