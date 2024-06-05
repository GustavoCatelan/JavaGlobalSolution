package br.com.fiap.javaGlobalSolution.service.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.pessoa.PessoaJuridicaRequest;
import br.com.fiap.javaGlobalSolution.dto.response.pessoa.PessoaJuridicaResponse;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Pessoa;
import br.com.fiap.javaGlobalSolution.entity.pessoa.PessoaJuridica;
import br.com.fiap.javaGlobalSolution.repository.pessoa.PessoaJuridicaRepository;
import br.com.fiap.javaGlobalSolution.service.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaJuridicaService implements ServiceDTO<PessoaJuridica, PessoaJuridicaRequest, PessoaJuridicaResponse> {

    @Autowired
    private final PessoaJuridicaRepository repo;

    @Override
    public PessoaJuridica toEntity(PessoaJuridicaRequest dto) {

        return PessoaJuridica.builder()
                .cnpj(dto.cnpj())
                .nomeFantasia(dto.nomeFantasia())
                .naturezaJuridica(dto.naturezaJuridica())
                .situacao(dto.situacao())
                .build();
    }

    @Override
    public PessoaJuridicaResponse toResponse(PessoaJuridica e) {

        return PessoaJuridicaResponse.builder()
                .id(e.getId())
                .cnpj(e.getCnpj())
                .nomeFantasia(e.getNomeFantasia())
                .naturezaJuridica(e.getNaturezaJuridica())
                .situacao(e.getSituacao())
                .build();
    }

    @Override
    public List<PessoaJuridica> findAll(Example<PessoaJuridica> example) {
        return repo.findAll(example);
    }

    @Override
    public PessoaJuridica findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public PessoaJuridica save(PessoaJuridica e) {
        return repo.save(e);
    }
}
