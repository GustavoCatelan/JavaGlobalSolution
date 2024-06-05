package br.com.fiap.javaGlobalSolution.service.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.pessoa.TelefoneRequest;
import br.com.fiap.javaGlobalSolution.dto.response.pessoa.TelefoneResponse;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Funcionario;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Telefone;
import br.com.fiap.javaGlobalSolution.repository.pessoa.TelefoneRepository;
import br.com.fiap.javaGlobalSolution.service.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TelefoneService implements ServiceDTO<Telefone, TelefoneRequest, TelefoneResponse> {

    @Autowired
    private final TelefoneRepository repo;

    @Autowired
    private final PessoaService pessoaService;

    @Override
    public Telefone toEntity(TelefoneRequest r) {

        var pessoa = pessoaService.findById(r.pessoa().id());

        return Telefone.builder()
                .ddi(r.ddi())
                .ddd(r.ddd())
                .numero(r.numero())
                .pessoa(pessoa)
                .build();

    }

    @Override
    public TelefoneResponse toResponse(Telefone e) {

        var pessoa = pessoaService.toResponse(e.getPessoa());

        return TelefoneResponse.builder()
                .id(e.getId())
                .ddi(e.getDdi())
                .ddd(e.getDdd())
                .numero(e.getNumero())
                .pessoa(pessoa)
                .build();


    }


    @Override
    public List<Telefone> findAll(Example<Telefone> example) {
        return repo.findAll(example);
    }

    @Override
    public Telefone findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Telefone save(Telefone e) {
        return repo.save(e);
    }

}
