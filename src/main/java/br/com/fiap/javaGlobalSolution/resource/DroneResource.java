package br.com.fiap.javaGlobalSolution.resource;

import br.com.fiap.javaGlobalSolution.dto.request.CoordenadaRequest;
import br.com.fiap.javaGlobalSolution.dto.request.DroneRequest;
import br.com.fiap.javaGlobalSolution.dto.response.CoordenadaResponse;
import br.com.fiap.javaGlobalSolution.dto.response.DroneResponse;
import br.com.fiap.javaGlobalSolution.entity.Coordenada;
import br.com.fiap.javaGlobalSolution.entity.Drone;
import br.com.fiap.javaGlobalSolution.service.CoordenadaService;
import br.com.fiap.javaGlobalSolution.service.DroneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/drone")
@RequiredArgsConstructor
public class DroneResource implements ResourceDTO<DroneRequest, DroneResponse>{

    @Autowired
    private  final DroneService service;

    @Autowired
    private final CoordenadaService coordenadaService;

    @GetMapping
    public ResponseEntity<Collection<DroneResponse>> findAll(

            @RequestParam(name = "modelo", required = false) String modelo,
            @RequestParam(name = "marca", required = false) String marca,
            @RequestParam(name = "distanciaMaxima", required = false) String distanciaMaxima,
            @RequestParam(name = "duracaoBateria", required = false) String duracaoBateria,
            @RequestParam(name = "dataFabricacao", required = false) LocalDate dataFabricacao
    ) {

        var drone = Drone.builder()
                .modelo(modelo)
                .marca(marca)
                .distanciaMaxima(distanciaMaxima)
                .duracaoBateria(duracaoBateria)
                .dataFabricacao(dataFabricacao)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<Drone> example = Example.of(drone, matcher);

        var entities = service.findAll(example);

        if (entities.isEmpty()) return ResponseEntity.notFound().build();

        var response = entities.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);

    }


    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<DroneResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<DroneResponse> save(@RequestBody @Valid DroneRequest r) {
        var entity = service.toEntity(r);
        service.save(entity);
        var response = service.toResponse(entity);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping(value = "/{id}/coordenada")
    public ResponseEntity<CoordenadaResponse> findCoordenadaByDrone(@PathVariable Long id) {
        var coordenada = coordenadaService.findById(id);
        var response = coordenadaService.toResponse(coordenada);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping(value = "/{id}/coordenada")
    public ResponseEntity<CoordenadaResponse> save(@PathVariable Long id, @RequestBody CoordenadaRequest coordenada) {

        Drone drone = service.findById(id);

        if (Objects.isNull(coordenada)) return ResponseEntity.badRequest().build();

        var entity = coordenadaService.toEntity(coordenada);

        entity.setDrone(drone);

        Coordenada saved = coordenadaService.save(entity);
        var response = coordenadaService.toResponse(saved);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
