package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.ClienteRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.ClienteResponseDTO;
import io.github.pietroow.real_estate_monitoring.dto.EnderecoResponseDTO;
import io.github.pietroow.real_estate_monitoring.model.Cliente;
import io.github.pietroow.real_estate_monitoring.model.Endereco;

public class ClienteMapper {

    public static Cliente toEntity(ClienteRequestDTO dto) {
        Endereco endereco = new Endereco(
                null,
                dto.endereco().cep(),
                dto.endereco().endereco(),
                dto.endereco().numero(),
                dto.endereco().complemento(),
                dto.endereco().bairro(),
                dto.endereco().estado(),
                dto.endereco().cidade()
        );

        return new Cliente(
                null,
                dto.nome(),
                dto.razaoSocial(),
                dto.tipo(),
                dto.cpfCnpj(),
                dto.inscricaoEstadual(),
                dto.inscricaoMunicipal(),
                dto.telefone1(),
                dto.telefone2(),
                dto.email(),
                dto.comentario(),
                endereco
        );
    }

    public static ClienteResponseDTO toDTO(Cliente cliente) {
        EnderecoResponseDTO enderecoDTO = null;

        if (cliente.getEndereco() != null) {
            enderecoDTO = new EnderecoResponseDTO(
                    cliente.getEndereco().getId(),
                    cliente.getEndereco().getCep(),
                    cliente.getEndereco().getEndereco(),
                    cliente.getEndereco().getNumero(),
                    cliente.getEndereco().getComplemento(),
                    cliente.getEndereco().getBairro(),
                    cliente.getEndereco().getEstado(),
                    cliente.getEndereco().getCidade()
            );
        }

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getRazaoSocial(),
                cliente.getTipo(),
                cliente.getCpfCnpj(),
                cliente.getTelefone1(),
                cliente.getTelefone2(),
                cliente.getEmail(),
                cliente.getComentario(),
                enderecoDTO
        );
    }
}