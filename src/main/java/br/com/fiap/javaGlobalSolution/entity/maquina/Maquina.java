package br.com.fiap.javaGlobalSolution.entity.maquina;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TB_MAQUINA")
public class Maquina {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MAQUINA")
    @SequenceGenerator(name = "SQ_MAQUINA", sequenceName = "SQ_MAQUINA", allocationSize = 1)
    @Column(name = "ID_MAQUINA")
    private Long id;

    @Column(name = "MODELO_MAQUINA")
    private String modelo;

    @Column(name = "MARCA_MAQUINA")
    private String marca;
}
