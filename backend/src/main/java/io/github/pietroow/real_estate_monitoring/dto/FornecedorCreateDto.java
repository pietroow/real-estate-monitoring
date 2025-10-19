package io.github.pietroow.real_estate_monitoring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record FornecedorCreateDto(

        @NotBlank
        @CNPJ(message = "CNPJ inválido")
        String cnpj,

        @NotBlank
        // Justificativa do min: razão social deve indicar tipo societário (ex.: S.A., LTDA.).
        @Size(min = 3, message = "Razão Social inválida")
        String razaoSocial,

        @NotBlank
        @Email
        String emailPrincipal,

        String nomeFantasia,
        String inscricaoEstadual,
        String inscricaoMunicipal,
        String telefonePrincipal,
        String nomeContato,
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        String bancoCodigo,
        String agencia,
        String conta,
        String pix,
        String observacoes
) {
}