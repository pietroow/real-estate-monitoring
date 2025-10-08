package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.StatusObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.obraResponseDTO.StatusObraResponseDTO;
import io.github.pietroow.real_estate_monitoring.mapper.StatusObraMapper;
import io.github.pietroow.real_estate_monitoring.model.StatusObra;
import io.github.pietroow.real_estate_monitoring.service.StatusObraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/status-obra")
@RequiredArgsConstructor
public class StatusObraController {

    private final StatusObraService statusObraService;
    private final StatusObraMapper statusObraMapper;

    @GetMapping
    public Page<StatusObraResponseDTO> listarTodosStatusObra(Pageable pageable) {
        Page<StatusObra> statusObraPagiandas = statusObraService.listarTodosStatusObra(pageable);
        return statusObraPagiandas.map(statusObraMapper::toStatusObraResponseDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarStatusObra(@RequestBody @Valid StatusObraRequestDTO dto) {
        StatusObra novoStatusObra = statusObraService.salvarStatusObra(dto);
        StatusObraResponseDTO responseDTO = statusObraMapper.toStatusObraResponseDTO(novoStatusObra);
    }

    @PutMapping("/{id}")
    public StatusObraResponseDTO atualizarStatusObra(@PathVariable UUID id, @RequestBody @Valid StatusObraRequestDTO dto) {
        StatusObra statusObraAtualizado = statusObraService.atualizarStatusObra(id, dto);
        return statusObraMapper.toStatusObraResponseDTO(statusObraAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarStatusObra(@PathVariable UUID id) {
        statusObraService.deletarStatusObra(id);
    }
}