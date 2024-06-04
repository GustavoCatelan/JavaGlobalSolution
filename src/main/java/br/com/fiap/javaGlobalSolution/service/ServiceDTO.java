package br.com.fiap.javaGlobalSolution.service;

import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface ServiceDTO<Entity, Request, Response> {


    Entity toEntity(Request r);

    Response toResponse(Entity e);

    List<Entity> findAll(Example<Entity> example);

    Optional<Entity> findById(Long id);

    Entity save(Entity e);

}

