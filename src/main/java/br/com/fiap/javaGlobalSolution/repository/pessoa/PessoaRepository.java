package br.com.fiap.javaGlobalSolution.repository.pessoa;

import br.com.fiap.javaGlobalSolution.entity.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
