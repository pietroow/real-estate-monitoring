package io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TipoObraRequestDTO(@NotBlank(message = "O nome do tipo da obra não pode ser vazio.")
                                 @Size(max = 255)
                                 String nome
) {
}