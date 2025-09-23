package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.FornecedorCreateDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorFilterDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorResponseDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorUpdateDto;
import io.github.pietroow.real_estate_monitoring.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @PostMapping
    public FornecedorResponseDto create(@Valid @RequestBody FornecedorCreateDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public Page<FornecedorResponseDto> list(FornecedorFilterDto filter, Pageable pageable) {
        return service.list(filter, pageable);
    }

    @GetMapping("/{id}")
    public FornecedorResponseDto get(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public FornecedorResponseDto update(@PathVariable UUID id, @Valid @RequestBody FornecedorUpdateDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.softDelete(id);
    }
}
