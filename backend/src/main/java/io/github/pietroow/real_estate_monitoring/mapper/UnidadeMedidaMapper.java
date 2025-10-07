package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.UnidadeMedidaRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.obraResponseDTO.UnidadeMedidaResponseDTO;
import io.github.pietroow.real_estate_monitoring.model.UnidadeMedida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadeMedidaMapper {

    UnidadeMedidaResponseDTO toUnidadeMedidaResponseDTO(UnidadeMedida UnidadeMedida);

    List<UnidadeMedidaResponseDTO> toListDTO(List<UnidadeMedida> tiposObra);

    @Mapping(target = "id", ignore = true)
    UnidadeMedida toEntity(UnidadeMedidaRequestDTO dto);
}
