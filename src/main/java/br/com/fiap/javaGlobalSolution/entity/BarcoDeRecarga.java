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
@Table(name = "TB_BARCO_DE_RECARGA")
public class BarcoDeRecarga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BARCO_DE_RECARGA")
    @SequenceGenerator(name = "SQ_BARCO_DE_RECARGA", sequenceName = "SQ_BARCO_DE_RECARGA", allocationSize = 1)
    @Column(name = "ID_BARCO_DE_RECARGA")
    private Long id;

    @Column(name = "MODELO_BARCO_DE_RECARGA")
    private String modelo;

    @Column(name = "QUANTIDADE_FUNCIONARIO_BARCO_DE_RECARGA")
    private String quantidadeFuncionario;

    @Column(name = "VELOCIDADE_MAXIMA_BARCO_DE_RECARGA")
    private String velocidadeMaxima;

    @Column(name = "SISTEMA_NAVEGACAO_BARCO_DE_RECARGA")
    private String sistemaNavegacao;

    @Column(name = "VELOCIDADE_RECARGA_BARCO_DE_RECARGA")
    private String velocidadeRecarga;
}
