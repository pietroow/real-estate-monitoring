package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.FuncionarioRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.FuncionarioResponseDTO;
import io.github.pietroow.real_estate_monitoring.model.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    Funcionario toEntity(FuncionarioRequestDTO requestDTO);

    FuncionarioResponseDTO toDto(Funcionario funcionario);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(FuncionarioRequestDTO requestDTO, @MappingTarget Funcionario funcionario);
}