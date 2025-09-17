package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.model.Obra;
import io.github.pietroow.real_estate_monitoring.service.ObraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}