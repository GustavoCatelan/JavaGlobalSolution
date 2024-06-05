package br.com.fiap.javaGlobalSolution.entity.pessoa;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder


@Entity
@Table(name = "TB_PESSOA_FISICA")
public class PessoaFisica extends Pessoa {

    @Column(name = "CPF_PESSOA_FISICA")
    private String cpf;

    @Column(name = "RG_PESSOA_FISICA")
    private  String rg;


}