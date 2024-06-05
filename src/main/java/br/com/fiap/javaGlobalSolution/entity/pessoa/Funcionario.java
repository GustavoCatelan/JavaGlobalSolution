package br.com.fiap.javaGlobalSolution.entity.pessoa;

import br.com.fiap.javaGlobalSolution.entity.maquina.BarcoDeRecarga;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "TB_FUNCIONARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_FUNCIONARIO_CPF", columnNames = {"CPF_FUNCIONARIO"}),
        @UniqueConstraint(name = "UK_FUNCIONARIO_PESSOA", columnNames = {"PESSOA"})
})
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FUNCIONARIO")
    @SequenceGenerator(name = "SQ_FUNCIONARIO", sequenceName = "SQ_FUNCIONARIO", allocationSize = 1)
    @Column(name = "ID_FUNCIONARIO")
    private Long id;

    @Column(name = "NOME_FUNCIONARIO")
    private String nome;

    @Column(name = "FUNCAO_FUNCIONARIO")
    private String funcao;

    @Column(name = "CPF_FUNCIONARIO")
    private String cpf;

    @Column(name = "EMAIL_FUNCIONARIO")
    private String email;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "BARCO_DE_RECARGA_FUNCIONARIO",
            joinColumns = {
                    @JoinColumn(
                            name = "FUNCIONARIO",
                            referencedColumnName = "ID_FUNCIONARIO",
                            foreignKey = @ForeignKey(name = "FK_FUNCIONARIO_BARCO_DE_RECARGA"))
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "BARCO_DE_RECARGA",
                            referencedColumnName = "ID_MAQUINA",
                            foreignKey = @ForeignKey(name = "FK_BARCO_DE_RECARGA_FUNCIONARIO"))
            }
    )
    private Set<BarcoDeRecarga> barcoDeRecarga = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "PESSOA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(
                    name = "FK_PESSOA_FUNCIONARIO"
            )
    )

    private Pessoa pessoa;

}
