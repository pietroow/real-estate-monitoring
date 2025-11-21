package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.EnderecoRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.EnderecoResponseDTO;
import io.github.pietroow.real_estate_monitoring.mapper.EnderecoMapper;
import io.github.pietroow.real_estate_monitoring.model.Endereco;
import io.github.pietroow.real_estate_monitoring.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final EnderecoMapper enderecoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoResponseDTO cadastrar(@Valid @RequestBody EnderecoRequestDTO dto) {
        Endereco novoEndereco = enderecoService.salvar(dto);
        return enderecoMapper.toEnderecoResponseDTO(novoEndereco);
    }

    @GetMapping("/{id}")
    public EnderecoResponseDTO buscarEnderecoPorId(@PathVariable UUID id) {
        Endereco enderecoId = enderecoService.buscarPorId(id);
        return enderecoMapper.toEnderecoResponseDTO(enderecoId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        enderecoService.deletar(id);
    }

    @PutMapping("/{id}")
    public EnderecoResponseDTO atualizar(@PathVariable UUID id, @Valid @RequestBody EnderecoRequestDTO dto) {
        Endereco enderecoAtualizado = enderecoService.atualizar(id, dto);
        return enderecoMapper.toEnderecoResponseDTO(enderecoAtualizado);
    }
}
