package io.github.pietroow.real_estate_monitoring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "funcionarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "cpf", unique = true, length = 11, nullable = false)
    private String cpf;

    @Column(name = "rg", nullable = false, length = 15)
    private String rg;

    @Column(name = "uf")
    private String uf;

    @Column(name = "telefone_1")
    private String telefone1;

    @Column(name = "telefone_2")
    private String telefone2;

    @Column(name = "email")
    private String email;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "quantidade_filhos")
    private int quantidadeFilhos;

    @Column(name = "nome_mae")
    private String nomeMae;

    @Column(name = "nome_pai")
    private String nomePai;

    @Column(name = "is_terceiro")
    private boolean isTerceiro;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "regime_de_trabalho")
    private String regimeDeTrabalho;

    @Column(name = "observacoes_remuneracao")
    private String observacoesRemuneracao;

    @Column(name = "funcao")
    private String funcao;

    @Column(name = "nivel")
    private String nivel;

    @Column(name = "data_admissao")
    private LocalDate dataAdmissao;

    @Column(name = "data_demissao")
    private LocalDate dataDemissao;

    @Column(name = "perfil")
    private String perfil;

    @Column(name = "conta_bancaria")
    private String contaBancaria;

    @Column(name = "empresa_referencia_1")
    private String empresaReferencia1;

    @Column(name = "contato_referencia_1")
    private String contatoReferencia1;

    @Column(name = "telefone_referencia_1")
    private String telefoneReferencia1;

    @Column(name = "empresa_referencia_2")
    private String empresaReferencia2;

    @Column(name = "contato_referencia_2")
    private String contatoReferencia2;

    @Column(name = "telefone_referencia_2")
    private String telefoneReferencia2;

}
