package io.github.pietroow.real_estate_monitoring.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class FuncionarioResponseDTO {

    private UUID id;
    private String nome;
    private String sexo;
    private String cpf;
    private String rg;
    private String uf;
    private String telefone1;
    private String telefone2;
    private String email;
    private LocalDate dataNascimento;
    private int quantidadeFilhos;
    private String nomeMae;
    private String nomePai;
    private boolean isTerceiro;
    private Boolean ativo;
    private String regimeDeTrabalho;
    private String observacoesRemuneracao;
    private String funcao;
    private String nivel;
    private LocalDate dataAdmissao;
    private LocalDate dataDemissao;
    private String perfil;
    private String contaBancaria;
    private String empresaReferencia1;
    private String contatoReferencia1;
    private String telefoneReferencia1;
    private String empresaReferencia2;
    private String contatoReferencia2;
    private String telefoneReferencia2;
    private String observacoesGeral;
}