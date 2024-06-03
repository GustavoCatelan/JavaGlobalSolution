package br.com.fiap.javaGlobalSolution.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "TB_COORDENADA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_COORDENADA_DRONE", columnNames = {"DRONE"}),
        @UniqueConstraint(name = "UK_COORDENADA_BARCO_DE_RECARGA", columnNames = {"BARCO_DE_RECARGA"})
})
public class Coordenada {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COORDENADA")
    @SequenceGenerator(name = "SQ_COORDENADA", sequenceName = "SQ_COORDENADA", allocationSize = 1)
    @Column(name = "ID_COORDENADA")
    private Long id;

    @Column(name = "LONGITUDE_COORDENADA")
    private Double longitude;

    @Column(name = "LATITUDE_COORDENADA")
    private Double latitude;

    @Column(name = "ALTITUDE_COORDENADA")
    private Double altitude;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "DRONE",
            referencedColumnName = "ID_DRONE",
            foreignKey = @ForeignKey(
                    name = "FK_DRONE_COORDENADA"
            )
    )

    private Drone drone;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "BARCO_DE_RECARGA",
            referencedColumnName = "ID_BARCO_DE_RECARGA",
            foreignKey = @ForeignKey(
                    name = "FK_BARCO_DE_RECARGA_COORDENADA"
            )
    )

    private BarcoDeRecarga barcoDeRecarga;
}
