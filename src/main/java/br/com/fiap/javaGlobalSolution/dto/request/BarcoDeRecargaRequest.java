package br.com.fiap.javaGlobalSolution.dto.request;

public record BarcoDeRecargaRequest(

        String modelo,

        String quantidadeFuncionario,

        String velocidadeMaxima,

        String sistemaNavegacao,

        String velocidadeRecarga


) {
}
