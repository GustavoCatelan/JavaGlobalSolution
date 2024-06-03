package br.com.fiap.javaGlobalSolution.dto.request;

public record FabricanteRequest (

        String nome,

        String email,

        String cnpj,

        String setor
){
}
