package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.FornecedorCreateDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorFilterDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorResponseDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorUpdateDto;
import io.github.pietroow.real_estate_monitoring.exception.ConflictException;
import io.github.pietroow.real_estate_monitoring.mapper.FornecedorMapper;
import io.github.pietroow.real_estate_monitoring.model.Fornecedor;
import io.github.pietroow.real_estate_monitoring.model.enums.FornecedorStatus;
import io.github.pietroow.real_estate_monitoring.repository.FornecedorRepository;
import io.github.pietroow.real_estate_monitoring.repository.FornecedorSpecifications;
import io.github.pietroow.real_estate_monitoring.util.CnpjUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FornecedorServiceImp implements FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorServiceImp(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public FornecedorResponseDto create(FornecedorCreateDto dto) {
        var maskedCnpj = CnpjUtils.toMasked(dto.cnpj());
        if (fornecedorRepository.existsByCnpj(maskedCnpj)) {
            throw new ConflictException("CNPJ já cadastrado");
        }
        var e = new Fornecedor();
        e.setStatus(FornecedorStatus.ATIVO);
        FornecedorMapper.applyCreate(e, dto);
        e.setCnpj(maskedCnpj);
        e = fornecedorRepository.save(e);
        return FornecedorMapper.toResponse(e);
    }

    @Override
    public FornecedorResponseDto getById(UUID id) {
        var e = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Fornecedor não encontrado"));
        return FornecedorMapper.toResponse(e);
    }

    @Override
    public Page<FornecedorResponseDto> list(FornecedorFilterDto filter, Pageable pageable) {
        return fornecedorRepository.findAll(FornecedorSpecifications.byFilter(filter), pageable)
                .map(FornecedorMapper::toResponse);
    }

    @Override
    public FornecedorResponseDto update(UUID id, FornecedorUpdateDto dto) {
        var e = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Fornecedor não encontrado"));

        if (dto.cnpj() != null) {
            var masked = CnpjUtils.toMasked(dto.cnpj());
            if (!masked.equals(e.getCnpj()) && fornecedorRepository.existsByCnpj(masked)) {
                throw new ConflictException("CNPJ já cadastrado em outro fornecedor");
            }
            FornecedorMapper.applyUpdate(e, new FornecedorUpdateDto(
                    masked,
                    dto.razaoSocial(), dto.emailPrincipal(), dto.nomeFantasia(),
                    dto.inscricaoEstadual(), dto.inscricaoMunicipal(), dto.telefonePrincipal(), dto.nomeContato(),
                    dto.cep(), dto.logradouro(), dto.numero(), dto.complemento(), dto.bairro(), dto.cidade(), dto.uf(),
                    dto.bancoCodigo(), dto.agencia(), dto.conta(), dto.pix(),
                    dto.observacoes(), dto.status()
            ));
        } else {
            FornecedorMapper.applyUpdate(e, dto);
        }

        e = fornecedorRepository.save(e);
        return FornecedorMapper.toResponse(e);
    }

    @Override
    public void softDelete(UUID id) {
        var e = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Fornecedor não encontrado"));
        e.setStatus(FornecedorStatus.INATIVO);
        fornecedorRepository.save(e);
    }
}
