package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.TipoObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.exceptionHandler.RegraDeNegocioException;
import io.github.pietroow.real_estate_monitoring.mapper.TipoObraMapper;
import io.github.pietroow.real_estate_monitoring.model.TipoObra;
import io.github.pietroow.real_estate_monitoring.repository.TipoObraRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TipoObraService {

    private final TipoObraRepository tipoObraRepository;
    private final TipoObraMapper tipoObraMapper;

    public Page<TipoObra> listarTodos(Pageable pageable) {
        return tipoObraRepository.findAll(pageable);
    }

    public TipoObra buscarPorId(UUID id) {
        return tipoObraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de Obra com ID" + id + "não encontrado."));
    }

    public TipoObra salvar(TipoObraRequestDTO dto) {
        tipoObraRepository.findByNome(dto.nome()).ifPresent(t -> {
            throw new RegraDeNegocioException("O tipo de obra '" + dto.nome() + "' já existe.");
        });
        TipoObra tipoObra = tipoObraMapper.toEntity(dto);
        return tipoObraRepository.save(tipoObra);
    }

    public TipoObra atualizar(UUID id, TipoObraRequestDTO dto) {
        TipoObra tipoObraExistente = this.buscarPorId(id);

        tipoObraRepository.findByNome(dto.nome()).ifPresent(t -> {
            if (!t.getId().equals(id)) {
                throw new RegraDeNegocioException("O tipo de obra '" + dto.nome() + "' já está em uso.");
            }
        });

        tipoObraExistente.setNome(dto.nome());
        return tipoObraRepository.save(tipoObraExistente);
    }

    public void deletar(UUID id) {
        TipoObra tipoObra = this.buscarPorId(id);
        tipoObraRepository.delete(tipoObra);
    }
}
