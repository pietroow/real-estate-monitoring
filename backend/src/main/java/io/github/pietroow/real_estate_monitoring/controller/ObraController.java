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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class ObraController {

    private final ObraService obraService;
    private final ObraMapper obraMapper;

    @PostMapping
    public ResponseEntity<ObraResponseDTO> cadastrarObra(@RequestBody @Valid ObraRequestDTO dto) {

        Obra novaObra = obraService.salvar(dto);
        ObraResponseDTO responseDTO = obraMapper.toResponseDTO(novaObra);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ObraResponseDTO>> listarObras(Pageable pageable) {

        Page<Obra> obrasPaginadas = obraService.listarObras(pageable);
        Page<ObraResponseDTO> dtoPage = obrasPaginadas.map(obraMapper::toResponseDTO);

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraResponseDTO> buscarObraPorId(@PathVariable UUID id) {

        Obra obraEncontrada = obraService.buscarPorId(id);
        ObraResponseDTO responseDTO = obraMapper.toResponseDTO(obraEncontrada);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarObras(@PathVariable UUID id) {
        obraService.deletarObra(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraResponseDTO> atualizarObras(@PathVariable UUID id, @RequestBody @Valid ObraRequestDTO dto) {
        Obra obraAtualizada = obraService.atualizarObra(id, dto);

        ObraResponseDTO responseDTO = obraMapper.toResponseDTO(obraAtualizada);
        return ResponseEntity.ok(responseDTO);
    }

}