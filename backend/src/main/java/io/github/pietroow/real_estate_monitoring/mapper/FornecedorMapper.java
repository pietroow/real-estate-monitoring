package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.FornecedorCreateDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorResponseDto;
import io.github.pietroow.real_estate_monitoring.model.Fornecedor;
import io.github.pietroow.real_estate_monitoring.model.enums.FornecedorStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {

    FornecedorResponseDto toFornecedorResponseDTO(Fornecedor fornecedor);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "status", source = "status")
    })
    Fornecedor toEntity(FornecedorCreateDto dto, String cnpj, FornecedorStatus status);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "cnpj", source = "cnpj"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "dataCadastro", ignore = true),
            @Mapping(target = "dataAtualizacao", ignore = true)
    })
    void updateEntityFromRequestDTO(@MappingTarget Fornecedor fornecedor, FornecedorCreateDto dto, String cnpj, FornecedorStatus status);
}