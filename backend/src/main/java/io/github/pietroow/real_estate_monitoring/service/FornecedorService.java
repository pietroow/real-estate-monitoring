package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.FornecedorCreateDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorFilterDto;
import io.github.pietroow.real_estate_monitoring.exceptionHandler.RegraDeNegocioException;
import io.github.pietroow.real_estate_monitoring.mapper.FornecedorMapper;
import io.github.pietroow.real_estate_monitoring.model.Fornecedor;
import io.github.pietroow.real_estate_monitoring.model.enums.FornecedorStatus;
import io.github.pietroow.real_estate_monitoring.repository.FornecedorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper mapper;

    @Transactional
    public Fornecedor create(FornecedorCreateDto dto) {
        String normalizedCnpj = dto.cnpj().replaceAll("\\D", "");
        if (fornecedorRepository.existsByCnpj(normalizedCnpj)) {
            throw new RegraDeNegocioException("CNPJ já cadastrado");
        }
        Fornecedor fornecedor = mapper.toEntity(dto, normalizedCnpj, FornecedorStatus.ATIVO);
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor findById(UUID id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado"));
    }

    public Page<Fornecedor> list(FornecedorFilterDto filter, Pageable pageable) {
        return fornecedorRepository.search(
                filter.cnpj(),
                filter.razaoSocial(),
                filter.nomeFantasia(),
                filter.cidade(),
                filter.uf(),
                filter.status() == null ? null : filter.status().name(),
                pageable
        );
    }

    @Transactional
    public Fornecedor update(UUID id, FornecedorCreateDto dto) {
        var fornecedor = this.findById(id);

        String normalized = dto.cnpj().replaceAll("\\D", "");

        if (!normalized.equals(fornecedor.getCnpj()) &&
                fornecedorRepository.existsByCnpjAndIdNot(normalized, id)) {
            throw new RegraDeNegocioException("CNPJ já cadastrado em outro fornecedor");
        }

        mapper.updateEntityFromRequestDTO(fornecedor, dto, normalized, fornecedor.getStatus());

        return fornecedorRepository.save(fornecedor);
    }

    @Transactional
    public void delete(UUID id) {
        var fornecedor = this.findById(id);
        fornecedor.inativar();
        fornecedorRepository.save(fornecedor);
    }
}