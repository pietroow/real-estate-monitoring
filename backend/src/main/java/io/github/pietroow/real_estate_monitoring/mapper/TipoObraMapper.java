package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.TipoObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.obraResponseDTO.TipoObraDTO;
import io.github.pietroow.real_estate_monitoring.model.TipoObra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoObraMapper {

    TipoObraDTO toDTO(TipoObra tipoObra);

    List<TipoObraDTO> toListDTO(List<TipoObra> tiposObra);

    @Mapping(target = "id", ignore = true)
    TipoObra toEntity(TipoObraRequestDTO dto);
}
