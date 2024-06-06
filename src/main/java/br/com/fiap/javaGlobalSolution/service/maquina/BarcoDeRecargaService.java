package br.com.fiap.javaGlobalSolution.service.maquina;

import br.com.fiap.javaGlobalSolution.dto.request.maquina.BarcoDeRecargaRequest;
import br.com.fiap.javaGlobalSolution.dto.response.maquina.BarcoDeRecargaResponse;
import br.com.fiap.javaGlobalSolution.entity.maquina.BarcoDeRecarga;
import br.com.fiap.javaGlobalSolution.repository.maquina.BarcoDeRecargaRepository;
import br.com.fiap.javaGlobalSolution.service.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarcoDeRecargaService implements ServiceDTO<BarcoDeRecarga, BarcoDeRecargaRequest, BarcoDeRecargaResponse> {

    @Autowired
    private final BarcoDeRecargaRepository repo;

    @Override
    public final BarcoDeRecarga toEntity(BarcoDeRecargaRequest r) {

        return BarcoDeRecarga.builder()
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
    public BarcoDeRecarga findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public BarcoDeRecarga save(BarcoDeRecarga e) {
        return repo.save(e);
    }
}
