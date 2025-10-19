package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.FornecedorCreateDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorFilterDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorResponseDto;
import io.github.pietroow.real_estate_monitoring.mapper.FornecedorMapper;
import io.github.pietroow.real_estate_monitoring.service.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;
    private final FornecedorMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FornecedorResponseDto create(@Valid @RequestBody FornecedorCreateDto dto) {
        return mapper.toFornecedorResponseDTO(fornecedorService.create(dto));
    }

    @GetMapping
    public Page<FornecedorResponseDto> list(FornecedorFilterDto filter, Pageable pageable) {
        return fornecedorService.list(filter, pageable).map(mapper::toFornecedorResponseDTO);
    }

    @GetMapping("/{id}")
    public FornecedorResponseDto get(@PathVariable UUID id) {
        return mapper.toFornecedorResponseDTO(fornecedorService.findById(id));
    }

    @PutMapping("/{id}")
    public FornecedorResponseDto update(@PathVariable UUID id, @Valid @RequestBody FornecedorCreateDto dto) {
        return mapper.toFornecedorResponseDTO(fornecedorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        fornecedorService.delete(id);
    }
}