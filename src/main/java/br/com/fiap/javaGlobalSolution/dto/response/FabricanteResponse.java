package br.com.fiap.javaGlobalSolution.dto.response;

import lombok.Builder;

@Builder
public record FabricanteResponse(

        Long id,

        String nome,

        String email,

        String cnpj,

        String setor
) {
}
