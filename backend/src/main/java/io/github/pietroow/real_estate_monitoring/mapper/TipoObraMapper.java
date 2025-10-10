package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.TipoObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.obraResponseDTO.TipoObraResponseDTO;
import io.github.pietroow.real_estate_monitoring.model.TipoObra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TipoObraMapper {

    TipoObraResponseDTO toTipoObraResponseDTO(TipoObra tipoObra);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "nome", source = "dto.nome")
    })
    TipoObra toEntity(TipoObraRequestDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "nome", source = "dto.nome")
    })
    void updateEntityFromRequestDTO(@MappingTarget TipoObra tipoObra, TipoObraRequestDTO dto);
}
