package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.FornecedorCreateDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorResponseDto;
import io.github.pietroow.real_estate_monitoring.model.Fornecedor;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {

    FornecedorResponseDto toResponse(Fornecedor e);

    void applyCreate(@MappingTarget Fornecedor e, FornecedorCreateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void applyUpdate(@MappingTarget Fornecedor e, FornecedorCreateDto dto);
}