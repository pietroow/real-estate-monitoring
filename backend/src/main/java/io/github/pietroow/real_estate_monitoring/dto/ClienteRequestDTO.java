package io.github.pietroow.real_estate_monitoring.dto;

import io.github.pietroow.real_estate_monitoring.model.enums.TipoPessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ClienteRequestDTO(

        String nome,
        String razaoSocial,
        String cpf,
        String cnpj,

        @NotNull(message = "O tipo de pessoa é obrigatório.")
        TipoPessoa tipo,

        String inscricaoEstadual,
        String inscricaoMunicipal,
        String telefone1,
        String telefone2,

        @Email(message = "O formato do e-mail é inválido.")
        String email,

        String comentario,

        @NotNull(message = "O endereço do cliente é obrigatório.")
        UUID enderecoId
) {
}
