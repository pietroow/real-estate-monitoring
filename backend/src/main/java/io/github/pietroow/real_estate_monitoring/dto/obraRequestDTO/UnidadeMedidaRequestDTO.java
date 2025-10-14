package io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UnidadeMedidaRequestDTO(
        @NotBlank(message = "O nome da unidade de medida não pode ser vazio.")
        @Size(max = 255)
        String nome
) {
}