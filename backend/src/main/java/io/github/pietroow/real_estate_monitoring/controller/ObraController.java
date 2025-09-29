package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.ObraDTO;
import io.github.pietroow.real_estate_monitoring.model.Obra;
import io.github.pietroow.real_estate_monitoring.service.ObraService;
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

    @PostMapping
    public ResponseEntity<Obra> cadastrarObra(@RequestBody Obra obra) {
        Obra novaObra = obraService.salvar(obra);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaObra);
    }

    @GetMapping
    public ResponseEntity<Page<Obra>> listarObras(Pageable pageable) {
        Page<Obra> obrasPaginadas = obraService.listarObras(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(obrasPaginadas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> buscarObraPorId(@PathVariable UUID id) {
        Obra obra = obraService.buscarPorId(id);
        return new ResponseEntity<>(obra, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarObras(@PathVariable UUID id) {
        obraService.deletarObra(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obra> atualizarObras(@PathVariable UUID id, @RequestBody ObraDTO dto) {
        Obra obraAtualizada = obraService.atualizarObra(id, dto);
        return ResponseEntity.ok(obraAtualizada);
    }

}