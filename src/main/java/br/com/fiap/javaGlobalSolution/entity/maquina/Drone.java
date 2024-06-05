package br.com.fiap.javaGlobalSolution.entity.maquina;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Fabricante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "TB_DRONE")
public class Drone extends Maquina{

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
