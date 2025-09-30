package io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ObraRequestDTO(
        @NotBlank(message = "O nome da obra não pode ser vazio.")
        String nome,

        @NotBlank(message = "O código da obra não pode ser vazio.")
        String codigo,

        @NotBlank(message = "A ART da obra não pode ser vazia.")
        String art,

        @NotBlank(message = "O responsável técnico não pode ser vazio.")
        String responsavelTecnico,

        @NotBlank(message = "O responsável pela obra não pode ser vazio.")
        String responsavelDaObra,

        String ceiCno,

        @NotNull(message = "A área total é obrigatória.")
        BigDecimal areaTotal,

        String comentario,

        boolean statusParaLancamentos,
        boolean statusParaFaturamentos,
        boolean statusParaCompras,

        @NotNull(message = "O ID do tipo da obra é obrigatório.")
        Integer tipoId,

        @NotNull(message = "O ID do status da obra é obrigatório.")
        Integer statusId,

        @NotNull(message = "O ID da unidade de medida é obrigatório.")
        Integer unidadeId) {
}
