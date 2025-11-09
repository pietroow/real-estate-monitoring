package io.github.pietroow.real_estate_monitoring.controller;

import io.github.pietroow.real_estate_monitoring.dto.ClienteRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.ClienteResponseDTO;
import io.github.pietroow.real_estate_monitoring.mapper.ClienteMapper;
import io.github.pietroow.real_estate_monitoring.model.Cliente;
import io.github.pietroow.real_estate_monitoring.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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


}