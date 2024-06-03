package br.com.fiap.javaGlobalSolution.repository;

import br.com.fiap.javaGlobalSolution.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {
}
