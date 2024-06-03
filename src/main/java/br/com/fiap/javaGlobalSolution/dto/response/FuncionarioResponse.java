package br.com.fiap.javaGlobalSolution.dto.response;

import lombok.Builder;

import java.util.Collection;

@Builder
public record FuncionarioResponse(

        Long id,

        String nome,

        String funcao,

        String cpf,

        String email,

        Collection<BarcoDeRecargaResponse> barcoDeRecarga
) {
}
