package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.model.Obra;
import io.github.pietroow.real_estate_monitoring.service.ObraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<List<Obra>> listarObras() {
        List<Obra> obras = obraService.listarTodasObras();
        return ResponseEntity.status(HttpStatus.OK).body(obras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> buscarObraPorId(@PathVariable UUID id) {
        Obra obra = obraService.buscarPorId(id);
        return new ResponseEntity<>(obra, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Obra> deletarObras(@PathVariable UUID id) {
        obraService.deletarObra(id);
        return new ResponseEntity<Obra>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Obra> atualizarObra(@PathVariable UUID id, @RequestBody Obra obra) {
        Obra obraAtualizada = obraService.atualizarObra(id, obra);
        return new ResponseEntity<Obra>(obraAtualizada, HttpStatus.OK);
    }



}