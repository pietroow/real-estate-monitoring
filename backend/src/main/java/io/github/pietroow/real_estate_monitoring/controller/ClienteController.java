package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.ClienteRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.ClienteResponseDTO;
import io.github.pietroow.real_estate_monitoring.mapper.ClienteMapper;
import io.github.pietroow.real_estate_monitoring.model.Cliente;
import io.github.pietroow.real_estate_monitoring.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO criar(@RequestBody @Valid ClienteRequestDTO dto) {
        Cliente novoCliente = clienteService.salvar(dto);
        return clienteMapper.toClienteResponseDTO(novoCliente);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO atualizar(@PathVariable UUID id, @RequestBody @Valid ClienteRequestDTO dto) {
        Cliente clienteAtualizado = clienteService.atualizar(id, dto);
        return clienteMapper.toClienteResponseDTO(clienteAtualizado);
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable UUID id) {
        Cliente clienteEncontrado = clienteService.buscarPorId(id);
        return clienteMapper.toClienteResponseDTO(clienteEncontrado);
    }

    @GetMapping
    public Page<ClienteResponseDTO> listarClientes(Pageable pageable) {
        Page<Cliente> clientesPaginados = clienteService.listar(pageable);
        return clientesPaginados.map(clienteMapper::toClienteResponseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        clienteService.deletar(id);
    }

}