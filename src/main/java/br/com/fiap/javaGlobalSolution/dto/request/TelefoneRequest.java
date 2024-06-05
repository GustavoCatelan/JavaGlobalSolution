package br.com.fiap.javaGlobalSolution.dto.request;

import org.springframework.lang.Nullable;

public record TelefoneRequest(

        String ddi,

        String ddd,

        String numero,

        AbstractRequest fabricante,

        AbstractRequest funcionario
) {
}
