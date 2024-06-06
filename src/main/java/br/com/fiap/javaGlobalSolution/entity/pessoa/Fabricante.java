package br.com.fiap.javaGlobalSolution.entity.pessoa;

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
@Table(name = "TB_FABRICANTE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_FABRICANTE_PESSOA", columnNames = {"PESSOA"})
})
public class Fabricante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FABRICANTE")
    @SequenceGenerator(name = "SQ_FABRICANTE", sequenceName = "SQ_FABRICANTE", allocationSize = 1)
    @Column(name = "ID_FABRICANTE")
    private Long id;

    @Column(name = "EMAIL_FABRICANTE")
    private String email;

    @Column(name = "SETOR_FABRICANTE")
    private String setor;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "FK_PESSOA_FABRICANTE"
            )
    )

    private Pessoa pessoa;
}
