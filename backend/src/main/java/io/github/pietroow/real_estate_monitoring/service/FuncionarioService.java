package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.FuncionarioRequestDTO;
import io.github.pietroow.real_estate_monitoring.mapper.FuncionarioMapper;
import io.github.pietroow.real_estate_monitoring.model.Funcionario;
import io.github.pietroow.real_estate_monitoring.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public Funcionario create(FuncionarioRequestDTO requestDTO) {
        Funcionario funcionario = funcionarioMapper.toEntity(requestDTO);
        return funcionarioRepository.save(funcionario);
    }

    public Page<Funcionario> findAll(Pageable pageable) {
        return funcionarioRepository.findAll(pageable);
    }

    public Funcionario findById(UUID id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o ID: " + id));
    }

    public Funcionario update(UUID id, FuncionarioRequestDTO requestDTO) {
        Funcionario funcionario = this.findById(id);
        funcionarioMapper.updateEntityFromDto(requestDTO, funcionario);
        return funcionarioRepository.save(funcionario);
    }

    public void delete(UUID id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado com o ID: " + id);
        }
        funcionarioRepository.deleteById(id);
    }
}