package br.com.fiap.javaGlobalSolution.service;

import br.com.fiap.javaGlobalSolution.dto.request.DroneRequest;
import br.com.fiap.javaGlobalSolution.dto.response.DroneResponse;
import br.com.fiap.javaGlobalSolution.entity.Drone;
import br.com.fiap.javaGlobalSolution.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneService implements ServiceDTO<Drone, DroneRequest, DroneResponse>{

    @Autowired
    private final DroneRepository repo;

    @Autowired
    private final FabricanteService fabricanteService;

    @Override
    public Drone toEntity(DroneRequest r) {

        var fabricante = fabricanteService.findById(r.fabricante().id());

        return Drone.builder()
                .modelo(r.modelo())
                .marca(r.marca())
                .distanciaMaxima(r.distanciaMaxima())
                .duracaoBateria(r.duracaoBateria())
                .dataFabricacao(r.dataFabricacao())
                .fabricante(fabricante)
                .build();
    }

    @Override
    public DroneResponse toResponse(Drone e) {

        var fabricante = fabricanteService.toResponse(e.getFabricante());

        return DroneResponse.builder()
                .id(e.getId())
                .modelo(e.getModelo())
                .marca(e.getMarca())
                .distanciaMaxima(e.getDistanciaMaxima())
                .duracaoBateria(e.getDuracaoBateria())
                .dataFabricacao(e.getDataFabricacao())
                .fabricante(fabricante)
                .build();
    }

    @Override
    public List<Drone> findAll(Example<Drone> example) {
        return repo.findAll(example);
    }

    @Override
    public Drone findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Drone save(Drone e) {
        return repo.save(e);
    }
}
