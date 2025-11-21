package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.ClienteRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.ClienteResponseDTO;
import io.github.pietroow.real_estate_monitoring.model.Cliente;
import io.github.pietroow.real_estate_monitoring.model.Endereco;
import io.github.pietroow.real_estate_monitoring.model.enums.TipoPessoa;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponseDTO toClienteResponseDTO(Cliente cliente);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "endereco", source = "endereco"),
            @Mapping(target = "nome", ignore = true),
            @Mapping(target = "razaoSocial", ignore = true),
            @Mapping(target = "cpf", ignore = true),
            @Mapping(target = "cnpj", ignore = true)
    })
    Cliente toEntity(ClienteRequestDTO dto, Endereco endereco);

    @AfterMapping
    default void mapearCamposPorTipo(ClienteRequestDTO dto, @MappingTarget Cliente cliente) {
        switch (dto.tipo()) {
            case PESSOA_FISICA -> mapearPessoaFisica(dto, cliente);
            case PESSOA_JURIDICA -> mapearPessoaJuridica(dto, cliente);
        }
    }

    private void mapearPessoaFisica(ClienteRequestDTO dto, Cliente cliente) {
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setRazaoSocial(null);
        cliente.setCnpj(null);
        cliente.setInscricaoEstadual(null);
        cliente.setInscricaoMunicipal(null);
    }

    private void mapearPessoaJuridica(ClienteRequestDTO dto, Cliente cliente) {
        cliente.setRazaoSocial(dto.razaoSocial());
        cliente.setCnpj(dto.cnpj());
        cliente.setInscricaoEstadual(dto.inscricaoEstadual());
        cliente.setInscricaoMunicipal(dto.inscricaoMunicipal());
        cliente.setNome(dto.razaoSocial());
        cliente.setCpf(null);
    }

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "endereco", source = "endereco"),
            @Mapping(target = "nome", ignore = true),
            @Mapping(target = "razaoSocial", ignore = true),
            @Mapping(target = "cpf", ignore = true),
            @Mapping(target = "cnpj", ignore = true)
    })
    void updateEntityFromDto(@MappingTarget Cliente cliente, ClienteRequestDTO dto, Endereco endereco);
}
