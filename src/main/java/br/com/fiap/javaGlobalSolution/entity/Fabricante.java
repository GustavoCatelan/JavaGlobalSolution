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
@Table(name = "TB_FABRICANTE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_FABRICANTE_CNPJ", columnNames = {"CNPJ_FABRICANTE"})
})
public class Fabricante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FABRICANTE")
    @SequenceGenerator(name = "SQ_FABRICANTE", sequenceName = "SQ_FABRICANTE", allocationSize = 1)
    @Column(name = "ID_FABRICANTE")
    private Long id;

    @Column(name = "NOME_FABRICANTE")
    private String nome;

    @Column(name = "EMAIL_FABRICANTE")
    private String email;

    @Column(name = "CNPJ_FABRICANTE")
    private String cnpj;

    @Column(name = "SETOR_FABRICANTE")
    private String setor;
}
