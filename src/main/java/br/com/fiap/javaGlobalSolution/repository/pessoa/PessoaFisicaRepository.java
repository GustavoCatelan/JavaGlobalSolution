package br.com.fiap.javaGlobalSolution.repository.pessoa;

import br.com.fiap.javaGlobalSolution.entity.pessoa.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

}
