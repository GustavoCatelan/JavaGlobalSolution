package br.com.fiap.javaGlobalSolution.resource.maquina;

import br.com.fiap.javaGlobalSolution.dto.request.maquina.CoordenadaRequest;
import br.com.fiap.javaGlobalSolution.dto.request.maquina.MaquinaRequest;
import br.com.fiap.javaGlobalSolution.dto.response.maquina.CoordenadaResponse;
import br.com.fiap.javaGlobalSolution.dto.response.maquina.MaquinaResponse;
import br.com.fiap.javaGlobalSolution.entity.maquina.Coordenada;
import br.com.fiap.javaGlobalSolution.entity.maquina.Drone;
import br.com.fiap.javaGlobalSolution.entity.maquina.Maquina;
import br.com.fiap.javaGlobalSolution.resource.ResourceDTO;
import br.com.fiap.javaGlobalSolution.service.maquina.CoordenadaService;
import br.com.fiap.javaGlobalSolution.service.maquina.MaquinaService;
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
@RequestMapping(value = "/maquina")
@RequiredArgsConstructor
public class MaquinaResource implements ResourceDTO<MaquinaRequest, MaquinaResponse> {

    @Autowired
    private final MaquinaService service;

    @Autowired
    private final CoordenadaService coordenadaService;

    @GetMapping
    public ResponseEntity<Collection<MaquinaResponse>> findAll(

            @RequestParam(name = "modelo", required = false) String modelo,
            @RequestParam(name = "marca", required = false) String marca
    ) {

        var maquina = Maquina.builder()
                .modelo(modelo)
                .marca(marca)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<Maquina> example = Example.of(maquina, matcher);

        var entities = service.findAll(example);

        if (entities.isEmpty()) return ResponseEntity.notFound().build();

        var response = entities.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);

    }


    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<MaquinaResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<MaquinaResponse> save(@RequestBody @Valid MaquinaRequest r) {
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

        Maquina maquina = service.findById(id);

        if (Objects.isNull(coordenada)) return ResponseEntity.badRequest().build();

        var entity = coordenadaService.toEntity(coordenada);

        entity.setMaquina(maquina);

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
