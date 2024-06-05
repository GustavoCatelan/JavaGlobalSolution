package br.com.fiap.javaGlobalSolution.resource;

import br.com.fiap.javaGlobalSolution.dto.request.BarcoDeRecargaRequest;
import br.com.fiap.javaGlobalSolution.dto.request.CoordenadaRequest;
import br.com.fiap.javaGlobalSolution.dto.response.BarcoDeRecargaResponse;
import br.com.fiap.javaGlobalSolution.dto.response.CoordenadaResponse;
import br.com.fiap.javaGlobalSolution.entity.BarcoDeRecarga;
import br.com.fiap.javaGlobalSolution.entity.Coordenada;
import br.com.fiap.javaGlobalSolution.service.BarcoDeRecargaService;
import br.com.fiap.javaGlobalSolution.service.CoordenadaService;
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
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/barcoDeRecarga")
@RequiredArgsConstructor
public class BarcoDeRecargaResource implements ResourceDTO<BarcoDeRecargaRequest, BarcoDeRecargaResponse>{

    @Autowired
    private final BarcoDeRecargaService service;

    @Autowired
    private final CoordenadaService coordenadaService;

    @GetMapping
    public ResponseEntity<Collection<BarcoDeRecargaResponse>> findAll(

            @RequestParam(name = "modelo", required = false) String modelo,
            @RequestParam(name = "quantidadeFuncionario", required = false) String quantidadeFuncionario,
            @RequestParam(name = "velocidadeMaxima", required = false) String velocidadeMaxima,
            @RequestParam(name = "sistemaNavegacao", required = false) String sistemaNavegacao,
            @RequestParam(name = "velocidadeRecarga", required = false) String velocidadeRecarga
    ) {

        var barcoDeRecarga = BarcoDeRecarga.builder()
                .modelo(modelo)
                .quantidadeFuncionario(quantidadeFuncionario)
                .velocidadeMaxima(velocidadeMaxima)
                .sistemaNavegacao(sistemaNavegacao)
                .velocidadeRecarga(velocidadeRecarga)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<BarcoDeRecarga> example = Example.of(barcoDeRecarga, matcher);

        var entities = service.findAll(example);

        if (entities.isEmpty()) return ResponseEntity.notFound().build();

        var response = entities.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);

    }


    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<BarcoDeRecargaResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<BarcoDeRecargaResponse> save(@RequestBody @Valid BarcoDeRecargaRequest r) {
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
    public ResponseEntity<CoordenadaResponse> findCoordenadaByBarcoDeRecarga(@PathVariable Long id) {
        var coordenada = coordenadaService.findById(id);
        var response = coordenadaService.toResponse(coordenada);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping(value = "/{id}/coordenada")
    public ResponseEntity<CoordenadaResponse> save(@PathVariable Long id, @RequestBody CoordenadaRequest coordenada) {

        BarcoDeRecarga barcoDeRecarga = service.findById(id);

        if (Objects.isNull(coordenada)) return ResponseEntity.badRequest().build();

        var entity = coordenadaService.toEntity(coordenada);

        entity.setBarcoDeRecarga(barcoDeRecarga);

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
