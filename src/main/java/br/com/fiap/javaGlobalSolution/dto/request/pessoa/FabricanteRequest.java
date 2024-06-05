package br.com.fiap.javaGlobalSolution.dto.request.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.AbstractRequest;

public record FabricanteRequest (

        String nome,

        String email,

        String cnpj,

        String setor,

        AbstractRequest pessoa
){
}
