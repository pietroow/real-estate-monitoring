package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.FornecedorCreateDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorFilterDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorResponseDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FornecedorService {
    FornecedorResponseDto create(FornecedorCreateDto dto);

    FornecedorResponseDto getById(UUID id);

    Page<FornecedorResponseDto> list(FornecedorFilterDto filter, Pageable pageable);

    FornecedorResponseDto update(UUID id, FornecedorUpdateDto dto);

    void softDelete(UUID id);
}
