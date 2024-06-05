package br.com.fiap.javaGlobalSolution.dto.request.maquina;

public record BarcoDeRecargaRequest(

        String modelo,

        String quantidadeFuncionario,

        String velocidadeMaxima,

        String sistemaNavegacao,

        String velocidadeRecarga


) {
}
