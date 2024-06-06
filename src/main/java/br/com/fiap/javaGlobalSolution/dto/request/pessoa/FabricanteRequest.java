package br.com.fiap.javaGlobalSolution.dto.request.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.AbstractRequest;

public record FabricanteRequest (

        String email,

        String setor,

        AbstractRequest pessoa
){
}
