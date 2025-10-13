package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.TipoObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.obraResponseDTO.TipoObraResponseDTO;
import io.github.pietroow.real_estate_monitoring.mapper.TipoObraMapper;
import io.github.pietroow.real_estate_monitoring.model.TipoObra;
import io.github.pietroow.real_estate_monitoring.service.TipoObraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tipos-obra")
@RequiredArgsConstructor
public class TipoObraController {

    private final TipoObraService tipoObraService;
    private final TipoObraMapper tipoObraMapper;

    @GetMapping
    public Page<TipoObraResponseDTO> listarTodosTiposObra(Pageable pageable) {
        Page<TipoObra> tipoObraPagiandas = tipoObraService.listarTodos(pageable);
        return tipoObraPagiandas.map(tipoObraMapper::toTipoObraResponseDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoObraResponseDTO cadastrarTipoObra(@RequestBody @Valid TipoObraRequestDTO dto) {
        TipoObra novoTipoObra = tipoObraService.salvar(dto);
        return tipoObraMapper.toTipoObraResponseDTO(novoTipoObra);
    }

    @PutMapping("/{id}")
    public TipoObraResponseDTO atualizarTipoObra(@PathVariable UUID id, @RequestBody @Valid TipoObraRequestDTO dto) {
        TipoObra tipoObraAtualizado = tipoObraService.atualizar(id, dto);
        return tipoObraMapper.toTipoObraResponseDTO(tipoObraAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarTipoObra(@PathVariable UUID id) {
        tipoObraService.deletar(id);
    }
}