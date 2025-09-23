package io.github.pietroow.real_estate_monitoring.mapper;

import io.github.pietroow.real_estate_monitoring.dto.FornecedorCreateDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorResponseDto;
import io.github.pietroow.real_estate_monitoring.dto.FornecedorUpdateDto;
import io.github.pietroow.real_estate_monitoring.model.Fornecedor;

public final class FornecedorMapper {
    private FornecedorMapper() {
    }

    public static FornecedorResponseDto toResponse(Fornecedor e) {
        return new FornecedorResponseDto(
                e.getId(), e.getCnpj(), e.getRazaoSocial(), e.getEmailPrincipal(),
                e.getNomeFantasia(), e.getInscricaoEstadual(), e.getInscricaoMunicipal(),
                e.getTelefonePrincipal(), e.getNomeContato(), e.getCep(), e.getLogradouro(),
                e.getNumero(), e.getComplemento(), e.getBairro(), e.getCidade(), e.getUf(),
                e.getBancoCodigo(), e.getAgencia(), e.getConta(), e.getPix(),
                e.getStatus(), e.getObservacoes()
        );
    }

    public static void applyCreate(Fornecedor e, FornecedorCreateDto dto) {
        e.setCnpj(dto.cnpj());
        e.setRazaoSocial(dto.razaoSocial());
        e.setEmailPrincipal(dto.emailPrincipal());
        e.setNomeFantasia(dto.nomeFantasia());
        e.setInscricaoEstadual(dto.inscricaoEstadual());
        e.setInscricaoMunicipal(dto.inscricaoMunicipal());
        e.setTelefonePrincipal(dto.telefonePrincipal());
        e.setNomeContato(dto.nomeContato());
        e.setCep(dto.cep());
        e.setLogradouro(dto.logradouro());
        e.setNumero(dto.numero());
        e.setComplemento(dto.complemento());
        e.setBairro(dto.bairro());
        e.setCidade(dto.cidade());
        e.setUf(dto.uf());
        e.setBancoCodigo(dto.bancoCodigo());
        e.setAgencia(dto.agencia());
        e.setConta(dto.conta());
        e.setPix(dto.pix());
        e.setObservacoes(dto.observacoes());
    }

    public static void applyUpdate(Fornecedor e, FornecedorUpdateDto dto) {
        if (dto.cnpj() != null) e.setCnpj(dto.cnpj());
        if (dto.razaoSocial() != null) e.setRazaoSocial(dto.razaoSocial());
        if (dto.emailPrincipal() != null) e.setEmailPrincipal(dto.emailPrincipal());
        if (dto.nomeFantasia() != null) e.setNomeFantasia(dto.nomeFantasia());
        if (dto.inscricaoEstadual() != null) e.setInscricaoEstadual(dto.inscricaoEstadual());
        if (dto.inscricaoMunicipal() != null) e.setInscricaoMunicipal(dto.inscricaoMunicipal());
        if (dto.telefonePrincipal() != null) e.setTelefonePrincipal(dto.telefonePrincipal());
        if (dto.nomeContato() != null) e.setNomeContato(dto.nomeContato());
        if (dto.cep() != null) e.setCep(dto.cep());
        if (dto.logradouro() != null) e.setLogradouro(dto.logradouro());
        if (dto.numero() != null) e.setNumero(dto.numero());
        if (dto.complemento() != null) e.setComplemento(dto.complemento());
        if (dto.bairro() != null) e.setBairro(dto.bairro());
        if (dto.cidade() != null) e.setCidade(dto.cidade());
        if (dto.uf() != null) e.setUf(dto.uf());
        if (dto.bancoCodigo() != null) e.setBancoCodigo(dto.bancoCodigo());
        if (dto.agencia() != null) e.setAgencia(dto.agencia());
        if (dto.conta() != null) e.setConta(dto.conta());
        if (dto.pix() != null) e.setPix(dto.pix());
        if (dto.observacoes() != null) e.setObservacoes(dto.observacoes());
        if (dto.status() != null) e.setStatus(dto.status());
    }
}
