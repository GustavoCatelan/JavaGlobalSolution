package br.com.fiap.javaGlobalSolution.service;

import br.com.fiap.javaGlobalSolution.dto.request.FabricanteRequest;
import br.com.fiap.javaGlobalSolution.dto.response.FabricanteResponse;
import br.com.fiap.javaGlobalSolution.entity.Fabricante;
import br.com.fiap.javaGlobalSolution.repository.FabricanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FabricanteService implements ServiceDTO<Fabricante, FabricanteRequest, FabricanteResponse>{

    @Autowired
    private final FabricanteRepository repo;

    @Override
    public Fabricante toEntity(FabricanteRequest r) {

        return Fabricante.builder()
                .nome(r.nome())
                .email(r.email())
                .cnpj(r.cnpj())
                .setor(r.setor())
                .build();
    }

    @Override
    public FabricanteResponse toResponse(Fabricante e) {

        return FabricanteResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .email(e.getEmail())
                .cnpj(e.getCnpj())
                .setor(e.getSetor())
                .build();
    }

    @Override
    public List<Fabricante> findAll(Example<Fabricante> example) {
        return repo.findAll(example);
    }

    @Override
    public Optional<Fabricante> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Fabricante save(Fabricante e) {
        return repo.save(e);
    }
}
