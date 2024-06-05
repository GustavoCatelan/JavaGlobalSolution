package br.com.fiap.javaGlobalSolution.resource;

import br.com.fiap.javaGlobalSolution.dto.request.EnderecoRequest;
import br.com.fiap.javaGlobalSolution.dto.request.FabricanteRequest;
import br.com.fiap.javaGlobalSolution.dto.request.TelefoneRequest;
import br.com.fiap.javaGlobalSolution.dto.response.EnderecoResponse;
import br.com.fiap.javaGlobalSolution.dto.response.FabricanteResponse;
import br.com.fiap.javaGlobalSolution.dto.response.TelefoneResponse;
import br.com.fiap.javaGlobalSolution.entity.Endereco;
import br.com.fiap.javaGlobalSolution.entity.Fabricante;
import br.com.fiap.javaGlobalSolution.entity.Telefone;
import br.com.fiap.javaGlobalSolution.service.EnderecoService;
import br.com.fiap.javaGlobalSolution.service.FabricanteService;
import br.com.fiap.javaGlobalSolution.service.TelefoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/fabricante")
@RequiredArgsConstructor
public class FabricanteResource implements ResourceDTO<FabricanteRequest, FabricanteResponse> {

    @Autowired
    private final FabricanteService service;

    @Autowired
    private final EnderecoService enderecoService;

    @Autowired
    private final TelefoneService telefoneService;

    @GetMapping
    public ResponseEntity<Collection<FabricanteResponse>> findAll(

            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "cnpj", required = false) String cnpj,
            @RequestParam(name = "setor", required = false) String setor
    ) {

        var fabricante = Fabricante.builder()
                .nome(nome)
                .email(email)
                .cnpj(cnpj)
                .setor(setor)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<Fabricante> example = Example.of(fabricante, matcher);

        var entities = service.findAll(example);

        if (entities.isEmpty()) return ResponseEntity.notFound().build();

        var response = entities.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);

    }


    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<FabricanteResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<FabricanteResponse> save(@RequestBody @Valid FabricanteRequest r) {
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

    @GetMapping(value = "/{id}/telefone")
    public ResponseEntity<TelefoneResponse> findTelefoneByFabricante(@PathVariable Long id) {
        var telefone = telefoneService.findById(id);
        var response = telefoneService.toResponse(telefone);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping(value = "/{id}/telefone")
    public ResponseEntity<TelefoneResponse> save(@PathVariable Long id, @RequestBody TelefoneRequest telefone) {

        Fabricante fabricante = service.findById(id);

        var entity = telefoneService.toEntity(telefone);

        entity.setFabricante(fabricante);

        Telefone saved = telefoneService.save(entity);
        var response = telefoneService.toResponse(saved);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping(value = "/{id}/endereco")
    public ResponseEntity<EnderecoResponse> findEnderecoByFabricante(@PathVariable Long id) {
        var endereco = enderecoService.findById(id);
        var response = enderecoService.toResponse(endereco);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping(value = "/{id}/endereco")
    public ResponseEntity<EnderecoResponse> save(@PathVariable Long id, @RequestBody EnderecoRequest endereco) {

        Fabricante fabricante = service.findById(id);

        var entity = enderecoService.toEntity(endereco);

        entity.setFabricante(fabricante);

        Endereco saved = enderecoService.save(entity);
        var response = enderecoService.toResponse(saved);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
