package br.com.fiap.javaGlobalSolution.entity.maquina;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "TB_BARCO_DE_RECARGA")
public class BarcoDeRecarga extends Maquina{

    @Column(name = "QUANTIDADE_FUNCIONARIO_BARCO_DE_RECARGA")
    private String quantidadeFuncionario;

    @Column(name = "VELOCIDADE_MAXIMA_BARCO_DE_RECARGA")
    private String velocidadeMaxima;

    @Column(name = "SISTEMA_NAVEGACAO_BARCO_DE_RECARGA")
    private String sistemaNavegacao;

    @Column(name = "VELOCIDADE_RECARGA_BARCO_DE_RECARGA")
    private String velocidadeRecarga;
}
