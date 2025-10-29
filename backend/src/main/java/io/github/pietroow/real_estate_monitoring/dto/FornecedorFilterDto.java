package io.github.pietroow.real_estate_monitoring.dto;

import io.github.pietroow.real_estate_monitoring.model.enums.FornecedorStatus;

public record FornecedorFilterDto(
        String cnpj,
        String razaoSocial,
        String nomeFantasia,
        FornecedorStatus status,
        String cidade,
        String uf
) {
}
