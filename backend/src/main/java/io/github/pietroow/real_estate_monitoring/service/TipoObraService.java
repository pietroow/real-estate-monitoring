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
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TipoObraService {

    private final TipoObraRepository tipoObraRepository;
    private final TipoObraMapper tipoObraMapper;

    @Transactional(readOnly = true)
    public Page<TipoObra> listarTodos(Pageable pageable) {
        return tipoObraRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public TipoObra buscarPorId(UUID id) {
        return tipoObraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de Obra com ID" + id + "não encontrado."));
    }

    @Transactional
    public TipoObra salvar(TipoObraRequestDTO dto) {

        if (tipoObraRepository.existsByNome(dto.nome())) {
            throw new RegraDeNegocioException("Tipo de obra: '" + dto.nome() + "' já está cadastrada.");
        }
        TipoObra novoTipoObra = tipoObraMapper.toEntity(dto);

        return tipoObraRepository.save(novoTipoObra);
    }

    @Transactional
    public TipoObra atualizar(UUID id, TipoObraRequestDTO dto) {
        TipoObra tipoObraExistente = this.buscarPorId(id);

        if (tipoObraRepository.existsByNome(dto.nome())) {
            throw new RegraDeNegocioException("Tipo de obra '" + dto.nome() + "' já está cadastrada.");
        }

        tipoObraMapper.updateEntityFromRequestDTO(tipoObraExistente, dto);

        return tipoObraRepository.save(tipoObraExistente);
    }

    @Transactional
    public void deletar(UUID id) {
        TipoObra tipoObra = this.buscarPorId(id);
        tipoObraRepository.delete(tipoObra);
    }
}
