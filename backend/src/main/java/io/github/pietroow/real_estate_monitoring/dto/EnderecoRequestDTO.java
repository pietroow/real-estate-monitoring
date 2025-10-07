package io.github.pietroow.real_estate_monitoring.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record EnderecoRequestDTO(

        @NotBlank
        String cep,
        @NotBlank
        String endereco,

        String numero,

        String complemento,

        String bairro,

        @NotBlank
        String estado,

        @NotBlank
        String cidade
) {
}
