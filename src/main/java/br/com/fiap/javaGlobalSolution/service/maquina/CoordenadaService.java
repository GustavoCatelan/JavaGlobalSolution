package br.com.fiap.javaGlobalSolution.service.maquina;

import br.com.fiap.javaGlobalSolution.dto.request.maquina.CoordenadaRequest;
import br.com.fiap.javaGlobalSolution.dto.response.maquina.CoordenadaResponse;
import br.com.fiap.javaGlobalSolution.entity.maquina.Coordenada;
import br.com.fiap.javaGlobalSolution.repository.maquina.CoordenadaRepository;
import br.com.fiap.javaGlobalSolution.service.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CoordenadaService implements ServiceDTO<Coordenada, CoordenadaRequest, CoordenadaResponse> {

    @Autowired
    private final CoordenadaRepository repo;

    @Autowired
    private final MaquinaService maquinaService;

    @Override
    public Coordenada toEntity(CoordenadaRequest r) {

        var maquina = maquinaService.findById(r.maquina().id());

        return Coordenada.builder()
                .longitude(r.longitude())
                .latitude(r.latitude())
                .altitude(r.altitude())
                .maquina(maquina)
                .build();
    }

    @Override
    public CoordenadaResponse toResponse(Coordenada e) {

        var maquina = maquinaService.toResponse(e.getMaquina());

        return CoordenadaResponse.builder()
                .id(e.getId())
                .longitude(e.getLongitude())
                .latitude(e.getLatitude())
                .altitude(e.getAltitude())
                .maquina(maquina)
                .build();
    }

    @Override
    public List<Coordenada> findAll(Example<Coordenada> example) {
        return repo.findAll(example);
    }

    @Override
    public Coordenada findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Coordenada save(Coordenada e) {
        return repo.save(e);
    }
}
