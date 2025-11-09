package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.EnderecoRequestDTO;
import io.github.pietroow.real_estate_monitoring.exceptionHandler.RegraDeNegocioException;
import io.github.pietroow.real_estate_monitoring.mapper.EnderecoMapper;
import io.github.pietroow.real_estate_monitoring.model.Endereco;
import io.github.pietroow.real_estate_monitoring.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    @Transactional
    public Endereco salvar(EnderecoRequestDTO dto) {

        if (enderecoRepository.existsByCepAndNumero(dto.cep(), dto.numero())) {
            throw new RegraDeNegocioException("Endereço com o CEP '" + dto.cep() + "' e número '" + dto.numero() + "' já está cadastrado.");
        }

        Endereco novoEndereco = enderecoMapper.toEntity(dto);

        return enderecoRepository.save(novoEndereco);

    }

    @Transactional(readOnly = true)
    public Page<Endereco> listar(Pageable pageable) {
        return enderecoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Endereco buscarPorId(UUID id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço com o ID '" + id + "' não encontrado."));
    }

    @Transactional
    public void deletar(UUID id) {
        Endereco endreco = this.buscarPorId(id);
        enderecoRepository.delete(endreco);
    }

    @Transactional
    public Endereco atualizar(UUID id, EnderecoRequestDTO dto) {
        Endereco enderecoExistente = this.buscarPorId(id);

        enderecoMapper.updateEntityFromRequestDTO(enderecoExistente, dto);

        return enderecoRepository.save(enderecoExistente);
    }
}
