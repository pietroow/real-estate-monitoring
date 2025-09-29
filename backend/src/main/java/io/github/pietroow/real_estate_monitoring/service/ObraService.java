package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.ObraAtualizacaoDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObraService {

    private final ObraRepository obraRepository;

    private TipoObraRepository tipoObraRepository;

    private StatusObraRepository statusObraRepository;

    private UnidadeMedidaRepository unidadeMedidaRepository;


    public Obra salvar(Obra obra) {
        return obraRepository.save(obra);
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

    public Obra atualizarObra(UUID id, ObraAtualizacaoDTO dto) {
        Obra obraExistente = obraRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Obra não encontrada com o ID: " + id));

        obraExistente.setNome(dto.getNome());
        obraExistente.setCodigo(dto.getCodigo());
        obraExistente.setArt(dto.getArt());
        obraExistente.setResponsavelTecnico(dto.getResponsavelTecnico());
        obraExistente.setResponsavelDaObra(dto.getResponsavelDaObra());
        obraExistente.setCeiCno(dto.getCeiCno());
        obraExistente.setAreaTotal(dto.getAreaTotal());
        obraExistente.setComentario(dto.getComentario());
        obraExistente.setStatusParaLancamentos(dto.isStatusParaLancamentos());
        obraExistente.setStatusParaFaturamentos(dto.isStatusParaFaturamentos());
        obraExistente.setStatusParaCompras(dto.isStatusParaCompras());

        TipoObra tipo = tipoObraRepository.findById(dto.getTipoId()).orElseThrow(() -> new EntityNotFoundException("Tipo de Obra não encontrado com o ID: " + dto.getTipoId()));

        StatusObra status = statusObraRepository.findById(dto.getStatusId()).orElseThrow(() -> new EntityNotFoundException("Status de Obra não encontrado com o ID: " + dto.getStatusId()));

        UnidadeMedida unidade = unidadeMedidaRepository.findById(dto.getUnidadeId()).orElseThrow(() -> new EntityNotFoundException("Unidade de Medida não encontrada com o ID: " + dto.getUnidadeId()));

        obraExistente.setTipoObra(tipo);
        obraExistente.setStatus(status);
        obraExistente.setUnidade(unidade);

        return obraRepository.save(obraExistente);

    }
}
