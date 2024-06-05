package br.com.fiap.javaGlobalSolution.repository.maquina;

import br.com.fiap.javaGlobalSolution.entity.maquina.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {
}
