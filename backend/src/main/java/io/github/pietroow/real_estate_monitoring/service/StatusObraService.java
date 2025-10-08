package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.StatusObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.exceptionHandler.RegraDeNegocioException;
import io.github.pietroow.real_estate_monitoring.mapper.StatusObraMapper;
import io.github.pietroow.real_estate_monitoring.model.StatusObra;
import io.github.pietroow.real_estate_monitoring.repository.StatusObraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatusObraService {

    private final StatusObraRepository statusObraRepository;
    private final StatusObraMapper statusObraMapper;

    public Page<StatusObra> listarTodosStatusObra(Pageable pageable) {
        return statusObraRepository.findAll(pageable);
    }

    public StatusObra buscarStatusObraPorId(UUID id) {
        return statusObraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status de Obra com ID '" + id + "' não encontrado."));
    }

    public StatusObra salvarStatusObra(StatusObraRequestDTO dto) {
        statusObraRepository.findByNome(dto.nome()).ifPresent(t -> {
            throw new RegraDeNegocioException("O status de obra '" + dto.nome() + "' já existe.");
        });
        StatusObra statusObra = statusObraMapper.toEntity(dto);
        return statusObraRepository.save(statusObra);
    }

    public StatusObra atualizarStatusObra(UUID id, StatusObraRequestDTO dto) {
        StatusObra statusObraExistente = this.buscarStatusObraPorId(id);

        statusObraRepository.findByNome(dto.nome()).ifPresent(t -> {
            if (!t.getId().equals(id)) {
                throw new RegraDeNegocioException("O status de obra '" + dto.nome() + "' já está em uso.");
            }
        });

        statusObraExistente.setNome(dto.nome());
        return statusObraRepository.save(statusObraExistente);
    }

    public void deletarStatusObra(UUID id) {
        StatusObra StatusObra = this.buscarStatusObraPorId(id);
        statusObraRepository.delete(StatusObra);
    }
}
