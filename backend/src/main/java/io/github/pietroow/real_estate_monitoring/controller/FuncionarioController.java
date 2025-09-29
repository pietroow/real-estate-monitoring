package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.FuncionarioRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.FuncionarioResponseDTO;
import io.github.pietroow.real_estate_monitoring.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> create(@RequestBody FuncionarioRequestDTO requestDTO) {
        FuncionarioResponseDTO responseDTO = funcionarioService.create(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<FuncionarioResponseDTO>> findAll(Pageable pageable) {
        Page<FuncionarioResponseDTO> page = funcionarioService.findAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> findById(@PathVariable UUID id) {
        Optional<FuncionarioResponseDTO> responseDTO = funcionarioService.findById(id);
        return responseDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> update(@PathVariable UUID id, @RequestBody FuncionarioRequestDTO requestDTO) {
        Optional<FuncionarioResponseDTO> responseDTO = funcionarioService.update(id, requestDTO);
        return responseDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try {
            funcionarioService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
