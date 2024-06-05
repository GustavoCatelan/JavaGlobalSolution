package br.com.fiap.javaGlobalSolution.service.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.pessoa.EnderecoRequest;
import br.com.fiap.javaGlobalSolution.dto.response.pessoa.EnderecoResponse;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Endereco;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Pessoa;
import br.com.fiap.javaGlobalSolution.repository.pessoa.EnderecoRepository;
import br.com.fiap.javaGlobalSolution.service.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EnderecoService implements ServiceDTO<Endereco, EnderecoRequest, EnderecoResponse> {

    @Autowired
    private final EnderecoRepository repo;

    @Autowired
    private final PessoaService pessoaService;

    @Override
    public Endereco toEntity(EnderecoRequest r) {

        var pessoa = pessoaService.findById(r.pessoa().id());

        return Endereco.builder()
                .cep(r.cep())
                .logradouro(r.logradouro())
                .numero(r.numero())
                .complemento(r.complemento())
                .cidade(r.cidade())
                .estado(r.estado())
                .pessoa(pessoa)
                .build();

    }

    @Override
    public EnderecoResponse toResponse(Endereco e) {

        var pessoa = pessoaService.toResponse(e.getPessoa());

        return EnderecoResponse.builder()
                .id(e.getId())
                .cep(e.getCep())
                .logradouro(e.getLogradouro())
                .numero(e.getNumero())
                .complemento(e.getComplemento())
                .cidade(e.getCidade())
                .estado(e.getEstado())
                .pessoa(pessoa)
                .build();
    }


    @Override
    public List<Endereco> findAll(Example<Endereco> example) {
        return repo.findAll(example);
    }

    @Override
    public Endereco findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Endereco save(Endereco e) {
        return repo.save(e);
    }
}
