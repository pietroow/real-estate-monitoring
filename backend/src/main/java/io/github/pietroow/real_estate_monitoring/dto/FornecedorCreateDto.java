package io.github.pietroow.real_estate_monitoring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FornecedorCreateDto(
        @NotBlank
        @Pattern(regexp = "(\\d{14})|(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})", message = "CNPJ inválido")
        @Size(max = 18)
        String cnpj,

        @NotBlank @Size(max = 255)
        String razaoSocial,

        @Email @NotBlank @Size(max = 100)
        String emailPrincipal,

        @Size(max = 255) String nomeFantasia,
        @Size(max = 20) String inscricaoEstadual,
        @Size(max = 20) String inscricaoMunicipal,
        @Size(max = 20) String telefonePrincipal,
        @Size(max = 100) String nomeContato,

        @Pattern(regexp = "(\\d{8})|(\\d{5}-\\d{3})", message = "CEP inválido")
        @Size(max = 9) String cep,
        @Size(max = 255) String logradouro,
        @Size(max = 20) String numero,
        @Size(max = 100) String complemento,
        @Size(max = 100) String bairro,
        @Size(max = 100) String cidade,
        @Pattern(regexp = "^[A-Z]{2}$", message = "UF inválido")
        String uf,

        @Size(max = 10) String bancoCodigo,
        @Size(max = 10) String agencia,
        @Size(max = 20) String conta,
        @Size(max = 255) String pix,

        String observacoes
) {
}
