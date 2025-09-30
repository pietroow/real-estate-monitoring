package io.github.pietroow.real_estate_monitoring.dto;

import java.util.UUID;

public record EnderecoResponseDTO(
        UUID id,
        String cep,
        String endereco,
        String numero,
        String complemento,
        String bairro,
        String estado,
        String cidade
) {
}
