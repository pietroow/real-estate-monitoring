package io.github.pietroow.real_estate_monitoring.dto;

import io.github.pietroow.real_estate_monitoring.model.TipoPessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ClienteRequestDTO(

        @NotBlank(message = "O nome do cliente não pode ser vazio.")
        String nome,

        @NotBlank(message = "A razão social não pode ser vazio.")
        String razaoSocial,

        @NotBlank(message = "O CPF ou CNPJ não pode ser vazio.")
        String cpfCnpj,

        @NotNull
        TipoPessoa tipo,

        String inscricaoEstadual,

        String inscricaoMunicipal,

        String telefone1,

        String telefone2,

        @Email
        String email,

        String comentario,

        @NotNull(message = "O endereço do cliente é obrigatório")
        UUID enderecoId
) {}
