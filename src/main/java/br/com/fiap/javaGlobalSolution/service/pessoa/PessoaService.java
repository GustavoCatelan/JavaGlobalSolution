package br.com.fiap.javaGlobalSolution.service.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.pessoa.PessoaRequest;
import br.com.fiap.javaGlobalSolution.dto.response.pessoa.PessoaResponse;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Pessoa;
import br.com.fiap.javaGlobalSolution.repository.pessoa.PessoaRepository;
import br.com.fiap.javaGlobalSolution.service.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService implements ServiceDTO<Pessoa, PessoaRequest, PessoaResponse> {
    @Autowired
    private final PessoaRepository repo;

    @Override
    public Pessoa toEntity(PessoaRequest dto) {

        return Pessoa.builder()
                .nome(dto.nome())
                .build();
    }

    @Override
    public PessoaResponse toResponse(Pessoa e) {

        return PessoaResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .build();
    }

    @Override
    public List<Pessoa> findAll(Example<Pessoa> example) {
        return repo.findAll(example);
    }

    @Override
    public Pessoa findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Pessoa save(Pessoa e) {
        return repo.save(e);
    }
}
