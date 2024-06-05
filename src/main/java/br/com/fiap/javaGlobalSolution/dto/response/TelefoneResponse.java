package br.com.fiap.javaGlobalSolution.dto.response;

import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record TelefoneResponse(

        Long id,

        String ddi,

        String ddd,

        String numero,

        FabricanteResponse fabricante,

        FuncionarioResponse funcionario
) {
}
