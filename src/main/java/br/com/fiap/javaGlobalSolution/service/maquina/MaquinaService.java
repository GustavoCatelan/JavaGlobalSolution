package br.com.fiap.javaGlobalSolution.service.maquina;

import br.com.fiap.javaGlobalSolution.dto.request.maquina.MaquinaRequest;
import br.com.fiap.javaGlobalSolution.dto.response.maquina.MaquinaResponse;
import br.com.fiap.javaGlobalSolution.entity.maquina.Maquina;
import br.com.fiap.javaGlobalSolution.repository.maquina.MaquinaRepository;
import br.com.fiap.javaGlobalSolution.service.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaquinaService implements ServiceDTO<Maquina, MaquinaRequest, MaquinaResponse> {

    @Autowired
    private MaquinaRepository repo;

    @Override
    public Maquina toEntity(MaquinaRequest dto) {

        return Maquina.builder()
                .modelo(dto.modelo())
                .marca(dto.marca())
                .build();
    }

    @Override
    public MaquinaResponse toResponse(Maquina e) {

        return MaquinaResponse.builder()
                .id(e.getId())
                .modelo(e.getModelo())
                .marca(e.getMarca())
                .build();
    }

    @Override
    public List<Maquina> findAll(Example<Maquina> example) {
        return repo.findAll(example);
    }

    @Override
    public Maquina findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public  Maquina save(Maquina e) {
        return repo.save(e);
    }
}
