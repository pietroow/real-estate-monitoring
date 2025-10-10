package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.StatusObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.TipoObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.obraResponseDTO.StatusObraResponseDTO;
import io.github.pietroow.real_estate_monitoring.model.StatusObra;
import io.github.pietroow.real_estate_monitoring.model.TipoObra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusObraMapper {

    StatusObraResponseDTO toStatusObraResponseDTO(StatusObra StatusObra);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "nome", source = "dto.nome")
    })
    StatusObra toEntity(StatusObraRequestDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "nome", source = "dto.nome")
    })
    void updateEntityFromRequestDTO(@MappingTarget StatusObra statusObra, StatusObraRequestDTO dto);
}
