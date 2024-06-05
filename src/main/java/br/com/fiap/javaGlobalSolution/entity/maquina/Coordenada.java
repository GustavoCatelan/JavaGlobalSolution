package br.com.fiap.javaGlobalSolution.entity.maquina;

import br.com.fiap.javaGlobalSolution.entity.pessoa.Pessoa;
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
        @UniqueConstraint(name = "UK_COORDENADA_MAQUINA", columnNames = {"MAQUINA"})
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
            name = "MAQUINA",
            referencedColumnName = "ID_MAQUINA",
            foreignKey = @ForeignKey(
                    name = "FK_MAQUINA_COORDENADA"
            )
    )

    private Maquina maquina;
}
