package io.github.pietroow.real_estate_monitoring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoRequestDTO(

        @NotBlank(message = "O CEP é obrigatório.")
        String cep,

        @NotBlank(message = "O endereço é obrigatório.")
        @Size(min = 3, max = 255)
        String endereco,

        @NotBlank(message = "O número é obrigatório.")
        @Size(max = 20, message = "máximo 20 caracteres.")
        String numero,

        @Size(max = 100, message = "máximo 100 caracteres.")
        String complemento,

        @NotBlank(message = "O bairro é obrigatório.")
        @Size(min = 2, max = 100, message = "máximo 100 caracteres.")
        String bairro,

        @NotBlank(message = "A cidade é obrigatória.")
        @Size(min = 2, max = 100, message = "máximo 100 caracteres.")
        String cidade,

        @NotBlank(message = "O estado é obrigatório.")
        @Size(min = 2, max = 100, message = "máximo 100 caracteres.")
        String estado
) {
}
