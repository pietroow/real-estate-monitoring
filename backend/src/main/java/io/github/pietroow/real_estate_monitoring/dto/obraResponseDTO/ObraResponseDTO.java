package io.github.pietroow.real_estate_monitoring.dto.obraResponseDTO;

import java.math.BigDecimal;
import java.util.UUID;

public record ObraResponseDTO(
        UUID id,
        String nome,
        String codigo,
        String art,
        String responsavelTecnico,
        String responsavelDaObra,
        String ceiCno,
        BigDecimal areaTotal,
        String comentario,
        boolean statusParaLancamentos,
        boolean statusParaFaturamentos,
        boolean statusParaCompras,
        TipoObraDTO tipo,
        StatusObraDTO status,
        UnidadeMedidaDTO unidade) {
}