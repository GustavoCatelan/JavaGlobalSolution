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
@Table(name = "TB_ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ENDERECO")
    @SequenceGenerator(name = "SQ_ENDERECO", sequenceName = "SQ_ENDERECO", allocationSize = 1)
    @Column(name = "ID_ENDERECO")
    private Long id;

    @Column(name = "CEP_ENDERECO")
    private String cep;

    @Column(name = "LOGRADOURO_ENDERECO")
    private  String logradouro;

    @Column(name = "NUM_ENDERECO")
    private String numero;

    @Column(name = "COMPLEMENTO_ENDERECO")
    private String complemento;

    @Column(name = "CIDADE_ENDERECO")
    private String cidade;

    @Column(name = "ESTADO_ENDERECO")
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "FABRICANTE",
            referencedColumnName = "ID_FABRICANTE",
            foreignKey = @ForeignKey(
                    name = "FK_ENDERECO_FABRICANTE"
            )
    )
    private Fabricante fabricante;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "FUNCIONARIO",
            referencedColumnName = "ID_FUNCIONARIO",
            foreignKey = @ForeignKey(
                    name = "FK_ENDERECO_FUNCIONARIO"
            )
    )
    private Funcionario funcionario;
}
