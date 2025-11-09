package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.ClienteRequestDTO;
import io.github.pietroow.real_estate_monitoring.exceptionHandler.RegraDeNegocioException;
import io.github.pietroow.real_estate_monitoring.mapper.ClienteMapper;
import io.github.pietroow.real_estate_monitoring.model.Cliente;
import io.github.pietroow.real_estate_monitoring.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Transactional
    public Cliente salvar(ClienteRequestDTO dto) {

        Cliente novoCliente = clienteMapper.toEntity(dto);

        return clienteRepository.save(novoCliente);
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
        Cliente cliente = this.buscarPorId(id);

        if (clienteRepository.existsByCpfOrCnpjAndIdNot(dto.cpfCnpj(), id)){
            throw new RegraDeNegocioException("CPF ou CNPJ '"+ dto.cpfCnpj() + " 'já está em uso por outro cliente")

        }

    };
}
