package br.com.fiap.javaGlobalSolution.dto.request.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.AbstractRequest;

public record TelefoneRequest(

        String ddi,

        String ddd,

        String numero,

        AbstractRequest pessoa
) {
}
