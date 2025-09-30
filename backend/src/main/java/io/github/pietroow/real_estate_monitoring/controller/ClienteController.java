package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.ClienteRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.ClienteResponseDTO;
import io.github.pietroow.real_estate_monitoring.mapper.ClienteMapper;
import io.github.pietroow.real_estate_monitoring.model.Cliente;
import io.github.pietroow.real_estate_monitoring.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ClienteResponseDTO criar(@RequestBody @Valid ClienteRequestDTO dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente salvo = clienteService.salvar(cliente);
        return ClienteMapper.toDTO(salvo);
    }

    @GetMapping
    public List<ClienteResponseDTO> listar() {
        return clienteService.listarTodos()
                .stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO buscar(@PathVariable UUID id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return ClienteMapper.toDTO(cliente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        clienteService.deletar(id);
    }
}