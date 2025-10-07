package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.UnidadeMedidaRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.obraResponseDTO.UnidadeMedidaResponseDTO;
import io.github.pietroow.real_estate_monitoring.mapper.UnidadeMedidaMapper;
import io.github.pietroow.real_estate_monitoring.model.UnidadeMedida;
import io.github.pietroow.real_estate_monitoring.service.UnidadeMedidaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/unidade-medida")
@RequiredArgsConstructor
public class UnidadeMedidaController {

    private final UnidadeMedidaService unidadeMedidaService;
    private final UnidadeMedidaMapper unidadeMedidaMapper;

    @GetMapping
    public Page<UnidadeMedidaResponseDTO> listarTodasUnidadesMedidas(Pageable pageable) {
        Page<UnidadeMedida> unidadeMedidaPagiandas = unidadeMedidaService.listarTodos(pageable);
        return unidadeMedidaPagiandas.map(unidadeMedidaMapper::toUnidadeMedidaResponseDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarUnidadeMedida(@RequestBody @Valid UnidadeMedidaRequestDTO dto) {
        UnidadeMedida novoUnidadeMedida = unidadeMedidaService.salvar(dto);
        UnidadeMedidaResponseDTO responseDTO = unidadeMedidaMapper.toUnidadeMedidaResponseDTO(novoUnidadeMedida);
    }

    @PutMapping("/{id}")
    public UnidadeMedidaResponseDTO atualizarUnidadeMedida(@PathVariable UUID id, @RequestBody @Valid UnidadeMedidaRequestDTO dto) {
        UnidadeMedida unidadeMedidaAtualizado = unidadeMedidaService.atualizar(id, dto);
        return unidadeMedidaMapper.toUnidadeMedidaResponseDTO(unidadeMedidaAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUnidadeMedida(@PathVariable UUID id) {
        unidadeMedidaService.deletar(id);
    }
}