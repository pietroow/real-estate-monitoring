package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.UnidadeMedidaRequestDTO;
import io.github.pietroow.real_estate_monitoring.exceptionHandler.RegraDeNegocioException;
import io.github.pietroow.real_estate_monitoring.mapper.UnidadeMedidaMapper;
import io.github.pietroow.real_estate_monitoring.model.UnidadeMedida;
import io.github.pietroow.real_estate_monitoring.repository.UnidadeMedidaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UnidadeMedidaService {

    private final UnidadeMedidaRepository unidadeMedidaRepository;
    private final UnidadeMedidaMapper unidadeMedidaMapper;

    @Transactional(readOnly = true)
    public Page<UnidadeMedida> listarTodos(Pageable pageable) {
        return unidadeMedidaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public UnidadeMedida buscarPorId(UUID id) {
        return unidadeMedidaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("A unidade de medida " + id + " não encontrado."));
    }

    @Transactional
    public UnidadeMedida salvar(UnidadeMedidaRequestDTO dto) {

        if (unidadeMedidaRepository.existsByNome(dto.nome())) {
            throw new RegraDeNegocioException("Tipo de obra: '" + dto.nome() + "' já está cadastrada.");
        }
        UnidadeMedida unidadeMedida = unidadeMedidaMapper.toEntity(dto);

        return unidadeMedidaRepository.save(unidadeMedida);
    }

    @Transactional
    public UnidadeMedida atualizar(UUID id, UnidadeMedidaRequestDTO dto) {
        UnidadeMedida unidadeMedidaExistente = this.buscarPorId(id);

        if (unidadeMedidaRepository.existsByNome(dto.nome())) {
            throw new RegraDeNegocioException("Unidade de medida '" + dto.nome() + "' já está cadastrada.");
        }

        unidadeMedidaMapper.updateEntityFromRequestDTO(unidadeMedidaExistente, dto);

        return unidadeMedidaRepository.save(unidadeMedidaExistente);
    }

    @Transactional
    public void deletar(UUID id) {
        UnidadeMedida unidadeMedida = this.buscarPorId(id);
        unidadeMedidaRepository.delete(unidadeMedida);
    }
}
