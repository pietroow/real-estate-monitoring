package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.ObraRequestDTO.ObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.ObraResponseDTO.ObraResponseDTO;
import io.github.pietroow.real_estate_monitoring.model.Obra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ObraMapper {

    ObraResponseDTO toResponseDTO(Obra obra);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "tipoObra", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "unidade", ignore = true)
    })
    Obra toEntity(ObraRequestDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "tipoObra", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "unidade", ignore = true)
    })
    void updateEntityFromRequestDTO(@MappingTarget Obra obra, ObraRequestDTO dto);
}
