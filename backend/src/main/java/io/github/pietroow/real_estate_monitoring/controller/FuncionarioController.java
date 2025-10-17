package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.FuncionarioRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.FuncionarioResponseDTO;
import io.github.pietroow.real_estate_monitoring.mapper.FuncionarioMapper;
import io.github.pietroow.real_estate_monitoring.model.Funcionario;
import io.github.pietroow.real_estate_monitoring.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
    private final FuncionarioMapper funcionarioMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioResponseDTO create(@RequestBody @Valid FuncionarioRequestDTO requestDTO) {
        Funcionario novoFuncionario = funcionarioService.create(requestDTO);
        return funcionarioMapper.toDto(novoFuncionario);
    }

    @GetMapping
    public Page<FuncionarioResponseDTO> findAll(Pageable pageable) {
        Page<Funcionario> pageDeFuncionarios = funcionarioService.findAll(pageable);
        return pageDeFuncionarios.map(funcionarioMapper::toDto);
    }

    @GetMapping("/{id}")
    public FuncionarioResponseDTO findById(@PathVariable UUID id) {
        Funcionario funcionario = funcionarioService.findById(id);
        return funcionarioMapper.toDto(funcionario);
    }

    @PutMapping("/{id}")
    public FuncionarioResponseDTO update(@PathVariable UUID id, @RequestBody @Valid FuncionarioRequestDTO requestDTO) {
        Funcionario funcionarioAtualizado = funcionarioService.update(id, requestDTO);
        return funcionarioMapper.toDto(funcionarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        funcionarioService.delete(id);
    }
}