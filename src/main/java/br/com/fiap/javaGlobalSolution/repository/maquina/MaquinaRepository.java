package br.com.fiap.javaGlobalSolution.repository.maquina;

import br.com.fiap.javaGlobalSolution.entity.maquina.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaquinaRepository extends JpaRepository<Maquina, Long> {
}
