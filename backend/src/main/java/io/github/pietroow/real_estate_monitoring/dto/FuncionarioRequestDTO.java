package io.github.pietroow.real_estate_monitoring.dto;

import java.time.LocalDate;

public record FuncionarioRequestDTO(
        String nome,
        String sexo,
        String cpf,
        String rg,
        String uf,
        String telefone1,
        String telefone2,
        String email,
        LocalDate dataNascimento,
        int quantidadeFilhos,
        String nomeMae,
        String nomePai,
        boolean isTerceiro,
        boolean ativo,
        String regimeDeTrabalho,
        String observacoesRemuneracao,
        String funcao,
        String nivel,
        LocalDate dataAdmissao,
        LocalDate dataDemissao,
        String perfil,
        String contaBancaria,
        String empresaReferencia1,
        String contatoReferencia1,
        String telefoneReferencia1,
        String empresaReferencia2,
        String contatoReferencia2,
        String telefoneReferencia2,
        String observacoesGeral
) {

}