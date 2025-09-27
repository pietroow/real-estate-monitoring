package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.FornecedorCreateDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorFilterDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorResponseDto;
import io.github.pietroow.real_estate_monitoring.dto.ValidationGroups;
import io.github.pietroow.real_estate_monitoring.mapper.FornecedorMapper;
import io.github.pietroow.real_estate_monitoring.service.FornecedorServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorServiceImp service;
    private final FornecedorMapper mapper;

    @PostMapping
    public FornecedorResponseDto create(@Validated(ValidationGroups.Create.class) @RequestBody FornecedorCreateDto dto) {
        var entity = service.create(dto);
        return mapper.toResponse(entity);
    }

    @GetMapping
    public Page<FornecedorResponseDto> list(FornecedorFilterDto filter, Pageable pageable) {
        return service.list(filter, pageable).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public FornecedorResponseDto get(@PathVariable UUID id) {
        var entity = service.getById(id);
        return mapper.toResponse(entity);
    }

    @PutMapping("/{id}")
    public FornecedorResponseDto update(@PathVariable UUID id, @Validated(ValidationGroups.Update.class) @RequestBody FornecedorCreateDto dto) {
        var entity = service.update(id, dto);
        return mapper.toResponse(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}