package io.github.pietroow.real_estate_monitoring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record FornecedorCreateDto(

        @NotBlank(groups = ValidationGroups.Create.class)
        @CNPJ(message = "CNPJ inválido", groups = ValidationGroups.Create.class)
        // CNPJ sem caracteres especiais ou formatação.
        @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos", groups = ValidationGroups.Update.class)
        String cnpj,

        @NotBlank(groups = ValidationGroups.Create.class)
        // Justificativa do min: razão social deve indicar tipo societário (ex.: S.A., LTDA.).
        @Size(min = 3, max = 255, message = "Razão Social inválida")
        String razaoSocial,

        @Email
        @NotBlank(groups = ValidationGroups.Create.class)
        @Size(max = 100, message = "E-mail inválido")
        String emailPrincipal,

        String nomeFantasia,
        String inscricaoEstadual,
        String inscricaoMunicipal,
        String telefonePrincipal,
        String nomeContato,

        @Pattern(regexp = "(\\d{8})|(\\d{5}-\\d{3})", message = "CEP inválido")
        String cep,

        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,

        @Pattern(regexp = "(?i)^[A-Z]{2}$", message = "UF inválido")
        String uf,

        String bancoCodigo,
        String agencia,
        String conta,
        String pix,

        String observacoes
) {
}