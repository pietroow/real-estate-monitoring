package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.ObraRequestDTO.TipoObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.ObraResponseDTO.TipoObraDTO;
import io.github.pietroow.real_estate_monitoring.mapper.TipoObraMapper;
import io.github.pietroow.real_estate_monitoring.model.TipoObra;
import io.github.pietroow.real_estate_monitoring.service.TipoObraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-obra")
@RequiredArgsConstructor
public class TipoObraController {

    private final TipoObraService tipoObraService;
    private final TipoObraMapper tipoObraMapper;

    @GetMapping
    public ResponseEntity<List<TipoObraDTO>> listarTodosTiposObra() {
        List<TipoObra> tiposObra = tipoObraService.listarTodos();
        List<TipoObraDTO> dtos = tipoObraMapper.toListDTO(tiposObra);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<TipoObraDTO> criarTipoObra(@RequestBody @Valid TipoObraRequestDTO dto) {
        TipoObra novoTipoObra = tipoObraService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoObraMapper.toDTO(novoTipoObra));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoObraDTO> atualizarTipoObra(@PathVariable Integer id, @RequestBody @Valid TipoObraRequestDTO dto) {
        TipoObra tipoObraAtualizado = tipoObraService.atualizar(id, dto);
        return ResponseEntity.ok(tipoObraMapper.toDTO(tipoObraAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTipoObra(@PathVariable Integer id) {
        tipoObraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
