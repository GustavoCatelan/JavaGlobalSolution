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
@Table(name = "TB_PESSOA_JURIDICA")
public class PessoaJuridica extends Pessoa{

    @Column(name = "CNPJ_PESSOA_JURIDICA")
    private String cnpj;

    @Column(name = "NM_FAN_PESSOA_JURIDICA")
    private String nomeFantasia;

    @Column(name = "NAT_JUR_PESSOA_JURIDICA")
    private String naturezaJuridica;

    @Column(name = "SIT_PESSOA_JURIDICA")
    private String situacao;

}
