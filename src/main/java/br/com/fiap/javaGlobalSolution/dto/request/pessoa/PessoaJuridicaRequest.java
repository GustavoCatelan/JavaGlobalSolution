package br.com.fiap.javaGlobalSolution.dto.request.pessoa;

public record PessoaJuridicaRequest(

        String cnpj,

        String nomeFantasia,

        String naturezaJuridica,

        String situacao
){
}
