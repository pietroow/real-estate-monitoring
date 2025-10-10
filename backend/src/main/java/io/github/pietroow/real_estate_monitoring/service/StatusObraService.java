package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.StatusObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.exceptionHandler.RegraDeNegocioException;
import io.github.pietroow.real_estate_monitoring.mapper.StatusObraMapper;
import io.github.pietroow.real_estate_monitoring.model.StatusObra;
import io.github.pietroow.real_estate_monitoring.repository.StatusObraRepository;
import io.github.pietroow.real_estate_monitoring.repository.TipoObraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatusObraService {

    private final StatusObraRepository statusObraRepository;
    private final StatusObraMapper statusObraMapper;
    private final TipoObraRepository tipoObraRepository;

    @Transactional(readOnly = true)
    public Page<StatusObra> listarTodosStatus(Pageable pageable) {
        return statusObraRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public StatusObra buscarPorId(UUID id) {
        return statusObraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status de Obra com ID '" + id + "' não encontrado."));
    }

    @Transactional
    public StatusObra salvar(StatusObraRequestDTO dto) {

        if (statusObraRepository.existsByNome(dto.nome())){
            throw new RegraDeNegocioException("Statutos de obra ''" + dto.nome() + "' já está cadastrado.");
        }
        StatusObra novoStatusObra = statusObraMapper.toEntity(dto);

        return statusObraRepository.save(novoStatusObra);
    }

    @Transactional
    public StatusObra atualizar(UUID id, StatusObraRequestDTO dto) {
        StatusObra statusObraExistente = this.buscarPorId(id);

        if(statusObraRepository.existsByNome(dto.nome())){
            throw  new RegraDeNegocioException("Statutos de obra '" + dto.nome() + "' já está cadastrado.");
        }

        statusObraMapper.updateEntityFromRequestDTO(statusObraExistente, dto);

        return statusObraRepository.save(statusObraExistente);
    }

    @Transactional
    public void deletar(UUID id) {
        StatusObra statusObra = this.buscarPorId(id);
        statusObraRepository.delete(statusObra);
    }
}
