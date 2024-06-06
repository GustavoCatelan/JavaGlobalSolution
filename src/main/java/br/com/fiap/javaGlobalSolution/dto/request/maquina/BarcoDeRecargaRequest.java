package br.com.fiap.javaGlobalSolution.dto.request.maquina;

public record BarcoDeRecargaRequest(

        String quantidadeFuncionario,

        String velocidadeMaxima,

        String sistemaNavegacao,

        String velocidadeRecarga


) {
}
