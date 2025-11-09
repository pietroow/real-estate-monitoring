package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.ClienteRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.ClienteResponseDTO;
import io.github.pietroow.real_estate_monitoring.model.Cliente;
import io.github.pietroow.real_estate_monitoring.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponseDTO toClienteResponseDTO(Cliente cliente);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "nome", ignore = true),
            @Mapping(target = "cnpj", ignore = true),
            @Mapping(target = "endereco", source = "endereco")
    })
    Cliente toEntity(ClienteRequestDTO dto, Endereco endereco);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "nome", ignore = true),
            @Mapping(target = "cnpj", ignore = true),
            @Mapping(target = "endereco", source = "endereco")
    })
    void updateEntityFromDto(@MappingTarget Cliente cliente, ClienteRequestDTO dto, Endereco endereco);
}
