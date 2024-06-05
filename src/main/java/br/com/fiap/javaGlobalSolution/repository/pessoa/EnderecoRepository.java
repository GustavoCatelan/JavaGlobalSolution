package br.com.fiap.javaGlobalSolution.repository.pessoa;

import br.com.fiap.javaGlobalSolution.entity.pessoa.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
