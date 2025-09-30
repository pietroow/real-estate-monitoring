package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.ObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.exceptionHandler.EntidadeNaoEncontradaException;
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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObraService {

    private final ObraRepository obraRepository;
    private final TipoObraRepository tipoObraRepository;
    private final StatusObraRepository statusObraRepository;
    private final UnidadeMedidaRepository unidadeMedidaRepository;
    private final ObraMapper obraMapper;

    public Obra salvar(ObraRequestDTO dto) {
        TipoObra tipo = tipoObraRepository.findById(dto.tipoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tipo de Obra com ID " + dto.tipoId() + " não encontrado."));

        StatusObra status = statusObraRepository.findById(dto.statusId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Status de Obra com ID " + dto.statusId() + " não encontrado."));

        UnidadeMedida unidade = unidadeMedidaRepository.findById(dto.unidadeId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Unidade de Medida com ID " + dto.unidadeId() + " não encontrada."));

        Obra novaObra = obraMapper.toEntity(dto);

        novaObra.setTipo(tipo);
        novaObra.setStatus(status);
        novaObra.setUnidade(unidade);

        return obraRepository.save(novaObra);
    }

    public Page<Obra> listarObras(Pageable pageable) {
        return obraRepository.findAll(pageable);
    }

    public Obra buscarPorId(UUID id) {
        return obraRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deletarObra(UUID id) {
        obraRepository.deleteById(id);
    }

    public Obra atualizarObra(UUID id, ObraRequestDTO dto) {
        // Busca a obra que será atualizada
        Obra obraExistente = obraRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Obra com ID " + id + " não encontrada."));


        Optional<Obra> obraComMesmaArt = obraRepository.findByArt(dto.art());

        obraComMesmaArt.ifPresent((Obra obraEncontrada) -> { // <-- CORREÇÃO: Tipo explícito (Obra)
            if (!obraEncontrada.getId().equals(id)) {
                throw new RegraDeNegocioException("A ART '" + dto.art() + "' já está em uso por outra obra.");
            }
        });

        TipoObra tipo = tipoObraRepository.findById(dto.tipoId()).orElseThrow(/*...*/);
        StatusObra status = statusObraRepository.findById(dto.statusId()).orElseThrow(/*...*/);
        UnidadeMedida unidade = unidadeMedidaRepository.findById(dto.unidadeId()).orElseThrow(/*...*/);

        obraMapper.updateEntityFromRequestDTO(obraExistente, dto);
        obraExistente.setTipo(tipo);
        obraExistente.setStatus(status);
        obraExistente.setUnidade(unidade);

        return obraRepository.save(obraExistente);
    }
}
