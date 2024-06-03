package br.com.fiap.javaGlobalSolution.repository;

import br.com.fiap.javaGlobalSolution.entity.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {
}
