package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.ObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.exceptionHandler.RegraDeNegocioException;
import io.github.pietroow.real_estate_monitoring.mapper.ObraMapper;
import io.github.pietroow.real_estate_monitoring.model.Obra;
import io.github.pietroow.real_estate_monitoring.model.StatusObra;
import io.github.pietroow.real_estate_monitoring.model.TipoObra;
import io.github.pietroow.real_estate_monitoring.model.UnidadeMedida;
import io.github.pietroow.real_estate_monitoring.repository.ObraRepository;
import io.github.pietroow.real_estate_monitoring.repository.StatusObraRepository;
import io.github.pietroow.real_estate_monitoring.repository.TipoObraRepository;
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
public class ObraService {

    private final ObraRepository obraRepository;
    private final TipoObraRepository tipoObraRepository;
    private final StatusObraRepository statusObraRepository;
    private final UnidadeMedidaRepository unidadeMedidaRepository;
    private final ObraMapper obraMapper;
    private final TipoObraService tipoObraService;
    private final StatusObraService statusObraService;
    private final UnidadeMedidaService unidadeMedidaService;

    @Transactional
    public Obra salvar(ObraRequestDTO dto) {
        TipoObra tipo = tipoObraService.buscarPorId(dto.tipoId());
        StatusObra status = statusObraService.buscarPorId(dto.statusId());
        UnidadeMedida unidade = unidadeMedidaService.buscarPorId(dto.unidadeId());

        Obra novaObra = obraMapper.toEntity(dto, tipo, status, unidade);

        return obraRepository.save(novaObra);
    }

    @Transactional(readOnly = true)
    public Page<Obra> listar(Pageable pageable) {
        return obraRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Obra buscarPorId(UUID id) {
        return obraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Obra com o ID '" + id + "' não encontrado."));
    }

    @Transactional
    public void deletar(UUID id) {
        Obra obra = this.buscarPorId(id);
        obraRepository.delete(obra);
    }

    @Transactional
    public Obra atualizar(UUID id, ObraRequestDTO dto) {
        Obra obraExistente = this.buscarPorId(id);

        if (obraRepository.existsByArtAndIdNot(dto.art(), id)) {
            throw new RegraDeNegocioException("Art '" + dto.art() + "'já está em uso por outra obra");
        }

        TipoObra tipo = tipoObraService.buscarPorId(dto.tipoId());
        StatusObra status = statusObraService.buscarPorId(dto.statusId());
        UnidadeMedida unidade = unidadeMedidaService.buscarPorId(dto.unidadeId());

        obraMapper.updateEntityFromRequestDTO(obraExistente, dto, tipo, status, unidade);

        return obraRepository.save(obraExistente);
    }
}
