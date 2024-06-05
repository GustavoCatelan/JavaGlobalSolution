package br.com.fiap.javaGlobalSolution.repository.pessoa;

import br.com.fiap.javaGlobalSolution.entity.pessoa.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
