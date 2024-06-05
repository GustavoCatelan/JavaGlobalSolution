package br.com.fiap.javaGlobalSolution.service.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.pessoa.FabricanteRequest;
import br.com.fiap.javaGlobalSolution.dto.response.pessoa.FabricanteResponse;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Fabricante;
import br.com.fiap.javaGlobalSolution.repository.pessoa.FabricanteRepository;
import br.com.fiap.javaGlobalSolution.service.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FabricanteService implements ServiceDTO<Fabricante, FabricanteRequest, FabricanteResponse> {

    @Autowired
    private final FabricanteRepository repo;

    @Autowired
    private final PessoaService pessoaService;

    @Override
    public Fabricante toEntity(FabricanteRequest r) {

        var pessoa = pessoaService.findById(r.pessoa().id());

        return Fabricante.builder()
                .nome(r.nome())
                .email(r.email())
                .cnpj(r.cnpj())
                .setor(r.setor())
                .pessoa(pessoa)
                .build();
    }

    @Override
    public FabricanteResponse toResponse(Fabricante e) {

        var pessoa = pessoaService.toResponse(e.getPessoa());

        return FabricanteResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .email(e.getEmail())
                .cnpj(e.getCnpj())
                .setor(e.getSetor())
                .pessoa(pessoa)
                .build();
    }

    @Override
    public List<Fabricante> findAll(Example<Fabricante> example) {
        return repo.findAll(example);
    }

    @Override
    public Fabricante findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Fabricante save(Fabricante e) {
        return repo.save(e);
    }
}
