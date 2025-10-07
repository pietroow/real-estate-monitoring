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

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UnidadeMedidaService {

    private final UnidadeMedidaRepository unidadeMedidaRepository;
    private final UnidadeMedidaMapper unidadeMedidaMapper;

    public Page<UnidadeMedida> listarTodos(Pageable pageable) {
        return unidadeMedidaRepository.findAll(pageable);
    }

    public UnidadeMedida buscarPorId(UUID id) {
        return unidadeMedidaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("A unidade de medida " + id + " não encontrado."));
    }

    public UnidadeMedida salvar(UnidadeMedidaRequestDTO dto) {
        unidadeMedidaRepository.findByNome(dto.nome()).ifPresent(t -> {
            throw new RegraDeNegocioException("A unidade de medida '" + dto.nome() + "' já existe.");
        });
        UnidadeMedida unidadeMedida = unidadeMedidaMapper.toEntity(dto);
        return unidadeMedidaRepository.save(unidadeMedida);
    }

    public UnidadeMedida atualizar(UUID id, UnidadeMedidaRequestDTO dto) {
        UnidadeMedida unidadeMedidaExistente = this.buscarPorId(id);

        unidadeMedidaRepository.findByNome(dto.nome()).ifPresent(t -> {
            if (!t.getId().equals(id)) {
                throw new RegraDeNegocioException("A unidade de medida '" + dto.nome() + "' já está em uso.");
            }
        });

        unidadeMedidaExistente.setNome(dto.nome());
        return unidadeMedidaRepository.save(unidadeMedidaExistente);
    }

    public void deletar(UUID id) {
        UnidadeMedida unidadeMedida = this.buscarPorId(id);
        unidadeMedidaRepository.delete(unidadeMedida);
    }
}
