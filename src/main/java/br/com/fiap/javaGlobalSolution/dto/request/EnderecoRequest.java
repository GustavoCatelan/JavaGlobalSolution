package br.com.fiap.javaGlobalSolution.dto.request;

public record EnderecoRequest (

        String cep,

        String logradouro,

        String numero,

        String complemento,

        String cidade,

        String estado,

        FabricanteRequest fabricante,

        FuncionarioRequest funcionario
){
}
