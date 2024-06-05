package br.com.fiap.javaGlobalSolution.repository.maquina;

import br.com.fiap.javaGlobalSolution.entity.maquina.Coordenada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordenadaRepository extends JpaRepository<Coordenada, Long> {
}
