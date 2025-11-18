package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.ClienteRequestDTO;
import io.github.pietroow.real_estate_monitoring.exceptionHandler.RegraDeNegocioException;
import io.github.pietroow.real_estate_monitoring.mapper.ClienteMapper;
import io.github.pietroow.real_estate_monitoring.model.Cliente;
import io.github.pietroow.real_estate_monitoring.model.Endereco;
import io.github.pietroow.real_estate_monitoring.model.enums.TipoPessoa;
import io.github.pietroow.real_estate_monitoring.repository.ClienteRepository;
import io.github.pietroow.real_estate_monitoring.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final EnderecoRepository enderecoRepository;

    @Transactional
    public Cliente salvar(ClienteRequestDTO dto) {

        validarDadosClientes(dto);

        Endereco endereco = enderecoRepository.findById(dto.enderecoId())
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado: " + dto.enderecoId()));

        Cliente novoCliente = clienteMapper.toEntity(dto, endereco);

        return clienteRepository.save(novoCliente);
    }

    private void validarDadosClientes(ClienteRequestDTO dto) {
        switch (dto.tipo()){
            case PESSOA_FISICA -> validarPessoaFisica(dto);
            case PESSOA_JURIDICA ->  validarPessoaJuridica(dto);
            default -> throw new RegraDeNegocioException("Seleção não válida.");
        }
    }

    private void validarPessoaFisica(ClienteRequestDTO dto) {
        if (!StringUtils.hasText(dto.nome())) {
            throw new RegraDeNegocioException("O nome é obrigatório para pessoa física.");
        }
        if (!StringUtils.hasText(dto.cpf())) {
            throw new RegraDeNegocioException("O CPF é obrigatório para pessoa física.");
        }
    }

    private void validarPessoaJuridica(ClienteRequestDTO dto) {
        if (!StringUtils.hasText(dto.razaoSocial())) {
            throw new RegraDeNegocioException("A razão social é obrigatória para pessoa jurídica.");
        }
        if (!StringUtils.hasText(dto.cnpj())) {
            throw new RegraDeNegocioException("O CNPJ é obrigatório para pessoa jurídica.");
        }
    }

    @Transactional(readOnly = true)
    public Page<Cliente> listar(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorId(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente com o ID '" + id + "' não encontrado."));
    }

    @Transactional
    public void deletar(UUID id) {
        Cliente cliente = this.buscarPorId(id);
        clienteRepository.delete(cliente);
    }

    @Transactional
    public Cliente atualizar(UUID id, ClienteRequestDTO dto) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID: " + id));

        validarDadosClientes(dto);

        Endereco endereco = enderecoRepository.findById(dto.enderecoId())
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado: " + dto.enderecoId()));

        clienteMapper.updateEntityFromDto(clienteExistente, dto, endereco);

        return clienteRepository.save(clienteExistente);
    }
}
