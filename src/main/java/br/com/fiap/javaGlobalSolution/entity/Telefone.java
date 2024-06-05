package br.com.fiap.javaGlobalSolution.entity;

import jakarta.annotation.Nullable;
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
@Table(name = "TB_TELEFONE",uniqueConstraints = {
        @UniqueConstraint(name = "UK_TELEFONE_NUMERO", columnNames = {"NUM_TELEFONE"})
})
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TELEFONE")
    @SequenceGenerator(name = "SQ_TELEFONE", sequenceName = "SQ_TELEFONE", allocationSize = 1)
    @Column(name = "ID_TELEFONE")
    private Long id;

    @Column(name = "DDI_TELEFONE")
    private String ddi;

    @Column(name = "DDD_TELEFONE")
    private String ddd;

    @Column(name = "NUM_TELEFONE")
    private String numero;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "FABRICANTE",
            referencedColumnName = "ID_FABRICANTE",
            foreignKey = @ForeignKey(
                    name = "FK_TELEFONE_FABRICANTE"
            )
    )
    private Fabricante fabricante;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "FUNCIONARIO",
            referencedColumnName = "ID_FUNCIONARIO",
            foreignKey = @ForeignKey(
                    name = "FK_TELEFONE_FUNCIONARIO"
            )
    )
    private Funcionario funcionario;
}
