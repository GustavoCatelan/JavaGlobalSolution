package br.com.fiap.javaGlobalSolution.service.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.pessoa.PessoaFisicaRequest;
import br.com.fiap.javaGlobalSolution.dto.response.pessoa.PessoaFisicaResponse;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Pessoa;
import br.com.fiap.javaGlobalSolution.entity.pessoa.PessoaFisica;
import br.com.fiap.javaGlobalSolution.repository.pessoa.PessoaFisicaRepository;
import br.com.fiap.javaGlobalSolution.service.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaFisicaService implements ServiceDTO<PessoaFisica, PessoaFisicaRequest, PessoaFisicaResponse> {


    @Autowired
    private final PessoaFisicaRepository repo;

    @Override
    public PessoaFisica toEntity(PessoaFisicaRequest dto) {

        return PessoaFisica.builder()
                .cpf(dto.cpf())
                .rg(dto.rg())
                .build();
    }

    @Override
    public PessoaFisicaResponse toResponse(PessoaFisica e) {

        return PessoaFisicaResponse.builder()
                .id(e.getId())
                .cpf(e.getCpf())
                .rg(e.getRg())
                .build();
    }

    @Override
    public List<PessoaFisica> findAll(Example<PessoaFisica> example) {
        return repo.findAll(example);
    }

    @Override
    public PessoaFisica findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public PessoaFisica save(PessoaFisica e) {
        return repo.save(e);
    }
}
