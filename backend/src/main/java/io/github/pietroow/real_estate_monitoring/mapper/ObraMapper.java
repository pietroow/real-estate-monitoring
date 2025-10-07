package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.ObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.obraResponseDTO.ObraResponseDTO;
import io.github.pietroow.real_estate_monitoring.model.Obra;
import io.github.pietroow.real_estate_monitoring.model.StatusObra;
import io.github.pietroow.real_estate_monitoring.model.TipoObra;
import io.github.pietroow.real_estate_monitoring.model.UnidadeMedida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ObraMapper {

    ObraResponseDTO toObraResponseDTO(Obra obra);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "nome", source = "dto.nome"),
            @Mapping(target = "tipo", source = "tipo"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "unidade", source = "unidade")
    })
    Obra toEntity(ObraRequestDTO dto, TipoObra tipo, StatusObra status , UnidadeMedida unidade);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "tipo", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "unidade", ignore = true)
    })
    void updateEntityFromRequestDTO(@MappingTarget Obra obra, ObraRequestDTO dto);
}
