package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.StatusObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.obraResponseDTO.StatusObraResponseDTO;
import io.github.pietroow.real_estate_monitoring.model.StatusObra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusObraMapper {

    StatusObraResponseDTO toStatusObraResponseDTO(StatusObra StatusObra);

    List<StatusObraResponseDTO> toListDTO(List<StatusObra> tiposObra);

    @Mapping(target = "id", ignore = true)
    StatusObra toEntity(StatusObraRequestDTO dto);
}
