package br.com.fiap.javaGlobalSolution.service;

import br.com.fiap.javaGlobalSolution.dto.request.BarcoDeRecargaRequest;
import br.com.fiap.javaGlobalSolution.dto.response.BarcoDeRecargaResponse;
import br.com.fiap.javaGlobalSolution.entity.BarcoDeRecarga;
import br.com.fiap.javaGlobalSolution.repository.BarcoDeRecargaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarcoDeRecargaService implements ServiceDTO<BarcoDeRecarga, BarcoDeRecargaRequest, BarcoDeRecargaResponse>{

    @Autowired
    private final BarcoDeRecargaRepository repo;

    @Override
    public final BarcoDeRecarga toEntity(BarcoDeRecargaRequest r) {

        return BarcoDeRecarga.builder()
                .modelo(r.modelo())
                .quantidadeFuncionario(r.quantidadeFuncionario())
                .velocidadeRecarga(r.velocidadeRecarga())
                .sistemaNavegacao(r.sistemaNavegacao())
                .velocidadeMaxima(r.velocidadeMaxima())
                .build();
    }

    @Override
    public BarcoDeRecargaResponse toResponse(BarcoDeRecarga e) {

        return BarcoDeRecargaResponse.builder()
                .id(e.getId())
                .modelo(e.getModelo())
                .quantidadeFuncionario(e.getQuantidadeFuncionario())
                .velocidadeRecarga(e.getVelocidadeRecarga())
                .sistemaNavegacao(e.getSistemaNavegacao())
                .velocidadeMaxima(e.getVelocidadeMaxima())
                .build();
    }

    @Override
    public List<BarcoDeRecarga> findAll(Example<BarcoDeRecarga> example) {
        return repo.findAll(example);
    }

    @Override
    public Optional<BarcoDeRecarga> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public BarcoDeRecarga save(BarcoDeRecarga e) {
        return repo.save(e);
    }
}
