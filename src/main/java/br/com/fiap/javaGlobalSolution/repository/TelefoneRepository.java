package br.com.fiap.javaGlobalSolution.repository;

import br.com.fiap.javaGlobalSolution.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
