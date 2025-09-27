package io.github.pietroow.real_estate_monitoring.dto;

import io.github.pietroow.real_estate_monitoring.model.enums.FornecedorStatus;

import java.util.UUID;

public record FornecedorResponseDto(
        UUID id,
        String cnpj,
        String razaoSocial,
        String emailPrincipal,
        String nomeFantasia,
        String inscricaoEstadual,
        String inscricaoMunicipal,
        String telefonePrincipal,
        String nomeContato,
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        String bancoCodigo,
        String agencia,
        String conta,
        String pix,
        FornecedorStatus status,
        String observacoes
) {
}
