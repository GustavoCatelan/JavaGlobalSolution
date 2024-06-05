package br.com.fiap.javaGlobalSolution.service;

import br.com.fiap.javaGlobalSolution.dto.request.EnderecoRequest;
import br.com.fiap.javaGlobalSolution.dto.response.EnderecoResponse;
import br.com.fiap.javaGlobalSolution.entity.Endereco;
import br.com.fiap.javaGlobalSolution.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService implements ServiceDTO<Endereco, EnderecoRequest, EnderecoResponse>{

    @Autowired
    private final EnderecoRepository repo;

    @Autowired
    private final FabricanteService fabricanteService;

    @Autowired
    private final FuncionarioService funcionarioService;

    @Override
    public Endereco toEntity(EnderecoRequest r) {

        var fabricante = fabricanteService.findById(r.fabricante().id());
        if (Objects.isNull(fabricante)){
            return null;
        }

        var funcionario = funcionarioService.findById(r.funcionario().id());
        if (Objects.isNull(funcionario)){
            return null;
        }

        return Endereco.builder()
                .cep(r.cep())
                .logradouro(r.logradouro())
                .numero(r.numero())
                .complemento(r.complemento())
                .cidade(r.cidade())
                .estado(r.estado())
                .fabricante(fabricante)
                .funcionario(funcionario)
                .build();

    }

    @Override
    public EnderecoResponse toResponse(Endereco e) {

        var fabricante = fabricanteService.toResponse(e.getFabricante());
        if (Objects.isNull(fabricante)){
            return null;
        }

        var funcionario = funcionarioService.toResponse(e.getFuncionario());
        if (Objects.isNull(funcionario)){
            return null;
        }

        return EnderecoResponse.builder()
                .id(e.getId())
                .cep(e.getCep())
                .logradouro(e.getLogradouro())
                .numero(e.getNumero())
                .complemento(e.getComplemento())
                .cidade(e.getCidade())
                .estado(e.getEstado())
                .fabricante(fabricante)
                .funcionario(funcionario)
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
