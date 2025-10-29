package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.FornecedorCreateDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorFilterDto;
import io.github.pietroow.real_estate_monitoring.exception.ConflictException;
import io.github.pietroow.real_estate_monitoring.exception.ResourceNotFoundException;
import io.github.pietroow.real_estate_monitoring.mapper.FornecedorMapper;
import io.github.pietroow.real_estate_monitoring.model.Fornecedor;
import io.github.pietroow.real_estate_monitoring.repository.FornecedorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class FornecedorServiceImp {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper mapper;

    public FornecedorServiceImp(FornecedorRepository fornecedorRepository, FornecedorMapper mapper) {
        this.fornecedorRepository = fornecedorRepository;
        this.mapper = mapper;
    }

    @Transactional
    public Fornecedor create(FornecedorCreateDto dto) {
        var normalizedCnpj = dto.cnpj().replaceAll("\\D", "");
        if (normalizedCnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ deve conter 14 dígitos");
        }
        if (fornecedorRepository.existsByCnpj(normalizedCnpj)) {
            throw new ConflictException("CNPJ já cadastrado");
        }
        var e = new Fornecedor();
        mapper.applyCreate(e, dto);
        e.setCnpj(normalizedCnpj);
        return fornecedorRepository.save(e);
    }

    @Transactional(readOnly = true)
    public Fornecedor getById(UUID id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));
    }

    @Transactional(readOnly = true)
    public Page<Fornecedor> list(FornecedorFilterDto filter, Pageable pageable) {
        String cnpj = null;
        if (filter != null && StringUtils.hasText(filter.cnpj())) {
            var onlyDigits = filter.cnpj().replaceAll("\\D", "");
            cnpj = onlyDigits.isEmpty() ? null : onlyDigits;
        }
        var razao = filter != null ? filter.razaoSocial() : null;
        var fantasia = filter != null ? filter.nomeFantasia() : null;
        var cidade = filter != null ? filter.cidade() : null;
        var uf = filter != null ? filter.uf() : null;
        var status = filter != null ? filter.status() : null;

        return fornecedorRepository.search(cnpj, razao, fantasia, cidade, uf, status, pageable);
    }

    @Transactional
    public Fornecedor update(UUID id, FornecedorCreateDto dto) {
        var e = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));

        if (dto.cnpj() != null) {
            var normalized = dto.cnpj().replaceAll("\\D", "");
            if (normalized.length() != 14) {
                throw new IllegalArgumentException("CNPJ deve conter 14 dígitos");
            }
            if (!normalized.equals(e.getCnpj()) && fornecedorRepository.existsByCnpj(normalized)) {
                throw new ConflictException("CNPJ já cadastrado em outro fornecedor");
            }
            var dtoWithNormalized = new FornecedorCreateDto(
                    normalized,
                    dto.razaoSocial(),
                    dto.emailPrincipal(),
                    dto.nomeFantasia(),
                    dto.inscricaoEstadual(),
                    dto.inscricaoMunicipal(),
                    dto.telefonePrincipal(),
                    dto.nomeContato(),
                    dto.cep(),
                    dto.logradouro(),
                    dto.numero(),
                    dto.complemento(),
                    dto.bairro(),
                    dto.cidade(),
                    dto.uf(),
                    dto.bancoCodigo(),
                    dto.agencia(),
                    dto.conta(),
                    dto.pix(),
                    dto.observacoes()
            );
            mapper.applyUpdate(e, dtoWithNormalized);
        } else {
            mapper.applyUpdate(e, dto);
        }

        return fornecedorRepository.save(e);
    }

    @Transactional
    public void delete(UUID id) {
        var e = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));
        e.inativar();
        fornecedorRepository.save(e);
    }
}