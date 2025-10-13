package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.obraRequestDTO.ObraRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.obraResponseDTO.ObraResponseDTO;
import io.github.pietroow.real_estate_monitoring.mapper.ObraMapper;
import io.github.pietroow.real_estate_monitoring.model.Obra;
import io.github.pietroow.real_estate_monitoring.service.ObraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class ObraController {

    private final ObraService obraService;
    private final ObraMapper obraMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ObraResponseDTO cadastrarObra(@RequestBody @Valid ObraRequestDTO dto) {
        Obra novaObra = obraService.salvar(dto);
        return obraMapper.toObraResponseDTO(novaObra);
    }

    @GetMapping
    public Page<ObraResponseDTO> listarObras(Pageable pageable) {
        Page<Obra> obrasPaginadas = obraService.listar(pageable);
        return obrasPaginadas.map(obraMapper::toObraResponseDTO);
    }

    @GetMapping("/{id}")
    public ObraResponseDTO buscarObraPorId(@PathVariable UUID id) {
        Obra obraEncontrada = obraService.buscarPorId(id);
        return obraMapper.toObraResponseDTO(obraEncontrada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarObras(@PathVariable UUID id) {
        obraService.deletar(id);
    }

    @PutMapping("/{id}")
    public ObraResponseDTO atualizarObras(@PathVariable UUID id, @RequestBody @Valid ObraRequestDTO dto) {
        Obra obraAtualizada = obraService.atualizar(id, dto);
        return obraMapper.toObraResponseDTO(obraAtualizada);
    }

}