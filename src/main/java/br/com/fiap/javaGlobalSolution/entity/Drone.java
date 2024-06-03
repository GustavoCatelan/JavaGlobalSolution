package br.com.fiap.javaGlobalSolution.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "TB_DRONE")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DRONE")
    @SequenceGenerator(name = "SQ_DRONE", sequenceName = "SQ_DRONE", allocationSize = 1)
    @Column(name = "ID_DRONE")
    private Long id;

    @Column(name = "MODELO_DRONE")
    private String modelo;

    @Column(name = "MARCA_DRONE")
    private String marca;

    @Column(name = "DISTANCIA_MAXIMA_DRONE")
    private String distanciaMaxima;

    @Column(name = "DURACAO_BATERIA_DRONE")
    private String duracaoBateria;

    @Column(name = "DATA_FABRICACAO_DRONE")
    private LocalDate dataFabricacao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "FABRICANTE",
            referencedColumnName = "ID_FABRICANTE",
            foreignKey = @ForeignKey(
                    name = "FK_DRONE_FABRICANTE"
            )
    )
    private Fabricante fabricante;

}
