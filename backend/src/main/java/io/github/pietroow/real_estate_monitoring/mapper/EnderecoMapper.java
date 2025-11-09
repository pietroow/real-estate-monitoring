package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.EnderecoRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.EnderecoResponseDTO;
import io.github.pietroow.real_estate_monitoring.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoResponseDTO toEnderecoResponseDTO(Endereco endereco);

    @Mapping(target = "id", ignore = true)
    Endereco toEntity(EnderecoRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequestDTO(@MappingTarget Endereco endereco, EnderecoRequestDTO dto);
}

