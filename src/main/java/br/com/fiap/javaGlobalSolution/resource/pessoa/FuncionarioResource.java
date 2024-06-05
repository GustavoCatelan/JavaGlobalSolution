package br.com.fiap.javaGlobalSolution.resource.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.pessoa.EnderecoRequest;
import br.com.fiap.javaGlobalSolution.dto.request.pessoa.FuncionarioRequest;
import br.com.fiap.javaGlobalSolution.dto.request.pessoa.TelefoneRequest;
import br.com.fiap.javaGlobalSolution.dto.response.pessoa.EnderecoResponse;
import br.com.fiap.javaGlobalSolution.dto.response.pessoa.FuncionarioResponse;
import br.com.fiap.javaGlobalSolution.dto.response.pessoa.TelefoneResponse;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Endereco;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Funcionario;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Telefone;
import br.com.fiap.javaGlobalSolution.resource.ResourceDTO;
import br.com.fiap.javaGlobalSolution.service.pessoa.EnderecoService;
import br.com.fiap.javaGlobalSolution.service.pessoa.FuncionarioService;
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
@RequestMapping(value = "/funcionario")
@RequiredArgsConstructor
public class FuncionarioResource implements ResourceDTO<FuncionarioRequest, FuncionarioResponse> {

    @Autowired
    private final FuncionarioService service;

    @GetMapping
    public ResponseEntity<Collection<FuncionarioResponse>> findAll(

            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "funcao", required = false) String funcao,
            @RequestParam(name = "cpf", required = false) String cpf,
            @RequestParam(name = "email", required = false) String email
    ) {

        var funcionario = Funcionario.builder()
                .nome(nome)
                .funcao(funcao)
                .cpf(cpf)
                .email(email)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<Funcionario> example = Example.of(funcionario, matcher);

        var entities = service.findAll(example);

        if (entities.isEmpty()) return ResponseEntity.notFound().build();

        var response = entities.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);

    }


    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<FuncionarioResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<FuncionarioResponse> save(@RequestBody @Valid FuncionarioRequest r) {
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
