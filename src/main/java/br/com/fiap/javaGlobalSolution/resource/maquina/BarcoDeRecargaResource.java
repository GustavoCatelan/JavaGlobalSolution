package br.com.fiap.javaGlobalSolution.resource.maquina;

import br.com.fiap.javaGlobalSolution.dto.request.maquina.BarcoDeRecargaRequest;
import br.com.fiap.javaGlobalSolution.dto.request.maquina.CoordenadaRequest;
import br.com.fiap.javaGlobalSolution.dto.response.maquina.BarcoDeRecargaResponse;
import br.com.fiap.javaGlobalSolution.dto.response.maquina.CoordenadaResponse;
import br.com.fiap.javaGlobalSolution.entity.maquina.BarcoDeRecarga;
import br.com.fiap.javaGlobalSolution.entity.maquina.Coordenada;
import br.com.fiap.javaGlobalSolution.resource.ResourceDTO;
import br.com.fiap.javaGlobalSolution.service.maquina.BarcoDeRecargaService;
import br.com.fiap.javaGlobalSolution.service.maquina.CoordenadaService;
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
public class BarcoDeRecargaResource implements ResourceDTO<BarcoDeRecargaRequest, BarcoDeRecargaResponse> {

    @Autowired
    private final BarcoDeRecargaService service;

    @Autowired
    private final CoordenadaService coordenadaService;

    @GetMapping
    public ResponseEntity<Collection<BarcoDeRecargaResponse>> findAll(

            @RequestParam(name = "quantidadeFuncionario", required = false) String quantidadeFuncionario,
            @RequestParam(name = "velocidadeMaxima", required = false) String velocidadeMaxima,
            @RequestParam(name = "sistemaNavegacao", required = false) String sistemaNavegacao,
            @RequestParam(name = "velocidadeRecarga", required = false) String velocidadeRecarga
    ) {

        var barcoDeRecarga = BarcoDeRecarga.builder()
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

}
