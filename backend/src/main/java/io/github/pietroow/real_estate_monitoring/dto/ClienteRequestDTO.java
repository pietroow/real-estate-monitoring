package io.github.pietroow.real_estate_monitoring.dto;

import io.github.pietroow.real_estate_monitoring.model.TipoPessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequestDTO(

        @NotBlank
        String nome,

        @NotBlank
        String razaoSocial,

        @NotBlank
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

        EnderecoRequestDTO endereco
) {}
