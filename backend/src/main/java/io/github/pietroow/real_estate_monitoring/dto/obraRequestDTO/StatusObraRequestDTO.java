package io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StatusObraRequestDTO(
        @NotBlank(message = "O nome do status da obra não pode ser vazio.")
        @Size(max = 255)
        String nome
) {
}