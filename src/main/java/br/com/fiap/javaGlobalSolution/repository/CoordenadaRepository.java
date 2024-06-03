package br.com.fiap.javaGlobalSolution.repository;

import br.com.fiap.javaGlobalSolution.entity.Coordenada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordenadaRepository extends JpaRepository<Coordenada, Long> {
}
