package br.com.fiap.javaGlobalSolution.repository;

import br.com.fiap.javaGlobalSolution.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
