package br.com.fiap.javaGlobalSolution.dto.request;

public record TelefoneRequest(

        String ddi,

        String ddd,

        String numero,

        AbstractRequest fabricante,

        AbstractRequest funcionario
) {
}
