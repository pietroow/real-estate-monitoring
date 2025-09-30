package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.TipoObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.exceptionHandler.EntidadeNaoEncontradaException;
import io.github.pietroow.real_estate_monitoring.exceptionHandler.RegraDeNegocioException;
import io.github.pietroow.real_estate_monitoring.mapper.TipoObraMapper;
import io.github.pietroow.real_estate_monitoring.model.TipoObra;
import io.github.pietroow.real_estate_monitoring.repository.TipoObraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoObraService {

    private final TipoObraRepository tipoObraRepository;
    private final TipoObraMapper tipoObraMapper;

    public List<TipoObra> listarTodos() {
        return tipoObraRepository.findAll();
    }

    public TipoObra salvar(TipoObraRequestDTO dto) {
        tipoObraRepository.findByNome(dto.nome()).ifPresent(t -> {
            throw new RegraDeNegocioException("O tipo de obra '" + dto.nome() + "' já existe.");
        });
        TipoObra tipoObra = tipoObraMapper.toEntity(dto);
        return tipoObraRepository.save(tipoObra);
    }

    public TipoObra atualizar(Integer id, TipoObraRequestDTO dto) {
        TipoObra tipoObraExistente = tipoObraRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tipo de Obra com ID " + id + " não encontrado."));

        tipoObraRepository.findByNome(dto.nome()).ifPresent(t -> {
            if (!t.getId().equals(id)) {
                throw new RegraDeNegocioException("O tipo de obra '" + dto.nome() + "' já está em uso.");
            }
        });

        tipoObraExistente.setNome(dto.nome());
        return tipoObraRepository.save(tipoObraExistente);
    }

    public void deletar(Integer id) {
        TipoObra tipoObra = tipoObraRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tipo de Obra com ID " + id + " não encontrado."));
        tipoObraRepository.delete(tipoObra);
    }
}
