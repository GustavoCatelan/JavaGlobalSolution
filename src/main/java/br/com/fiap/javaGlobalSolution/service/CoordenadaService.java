package br.com.fiap.javaGlobalSolution.service;

import br.com.fiap.javaGlobalSolution.dto.request.CoordenadaRequest;
import br.com.fiap.javaGlobalSolution.dto.response.CoordenadaResponse;
import br.com.fiap.javaGlobalSolution.entity.Coordenada;
import br.com.fiap.javaGlobalSolution.repository.CoordenadaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoordenadaService implements ServiceDTO<Coordenada, CoordenadaRequest, CoordenadaResponse> {

    @Autowired
    private final CoordenadaRepository repo;

    @Autowired
    private final DroneService droneService;

    @Autowired
    private final BarcoDeRecargaService barcoDeRecargaService;

    @Override
    public Coordenada toEntity(CoordenadaRequest r) {

        var drone = droneService.findById(r.drone().id());
        if (Objects.isNull(drone)){
            return null;
        }

        var barcoDeRecarga = barcoDeRecargaService.findById(r.barcoDeRecarga().id());
        if (Objects.isNull(barcoDeRecarga)){
            return null;
        }

        return Coordenada.builder()
                .longitude(r.longitude())
                .latitude(r.latitude())
                .altitude(r.altitude())
                .drone(drone)
                .barcoDeRecarga(barcoDeRecarga)
                .build();
    }

    @Override
    public CoordenadaResponse toResponse(Coordenada e) {

        var drone = droneService.toResponse(e.getDrone());
        if (Objects.isNull(drone)){
            return null;
        }

        var barcoDeRecarga = barcoDeRecargaService.toResponse(e.getBarcoDeRecarga());
        if (Objects.isNull(barcoDeRecarga)){
            return null;
        }

        return CoordenadaResponse.builder()
                .id(e.getId())
                .longitude(e.getLongitude())
                .latitude(e.getLatitude())
                .altitude(e.getAltitude())
                .drone(drone)
                .barcoDeRecarga(barcoDeRecarga)
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
