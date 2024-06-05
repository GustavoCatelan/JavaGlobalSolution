package br.com.fiap.javaGlobalSolution.resource.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.pessoa.EnderecoRequest;
import br.com.fiap.javaGlobalSolution.dto.request.pessoa.FabricanteRequest;
import br.com.fiap.javaGlobalSolution.dto.request.pessoa.TelefoneRequest;
import br.com.fiap.javaGlobalSolution.dto.response.pessoa.EnderecoResponse;
import br.com.fiap.javaGlobalSolution.dto.response.pessoa.FabricanteResponse;
import br.com.fiap.javaGlobalSolution.dto.response.pessoa.TelefoneResponse;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Endereco;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Fabricante;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Telefone;
import br.com.fiap.javaGlobalSolution.resource.ResourceDTO;
import br.com.fiap.javaGlobalSolution.service.pessoa.EnderecoService;
import br.com.fiap.javaGlobalSolution.service.pessoa.FabricanteService;
import br.com.fiap.javaGlobalSolution.service.pessoa.TelefoneService;
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
@RequestMapping(value = "/fabricante")
@RequiredArgsConstructor
public class FabricanteResource implements ResourceDTO<FabricanteRequest, FabricanteResponse> {

    @Autowired
    private final FabricanteService service;

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

}
