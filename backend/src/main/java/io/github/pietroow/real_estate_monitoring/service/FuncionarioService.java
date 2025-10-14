package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.FuncionarioRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.FuncionarioResponseDTO;
import io.github.pietroow.real_estate_monitoring.model.Funcionario;
import io.github.pietroow.real_estate_monitoring.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioResponseDTO create(FuncionarioRequestDTO requestDTO) {
        Funcionario funcionario = funcionarioMapper.toEntity(requestDTO);
        funcionario = funcionarioRepository.save(funcionario);
        return funcionarioMapper.toDto(funcionario);
    }

    public Page<FuncionarioResponseDTO> findAll(Pageable pageable) {
        Page<Funcionario> funcionariosPage = funcionarioRepository.findAll(pageable);
        return funcionariosPage.map(funcionarioMapper::toDto);
    }

    public Optional<FuncionarioResponseDTO> findById(UUID id) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
        return optionalFuncionario.map(funcionarioMapper::toDto);
    }

    public Optional<FuncionarioResponseDTO> update(UUID id, FuncionarioRequestDTO requestDTO) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
        if (optionalFuncionario.isPresent()) {
            Funcionario funcionario = optionalFuncionario.get();
            funcionarioMapper.updateEntityFromDto(requestDTO, funcionario);
            funcionario = funcionarioRepository.save(funcionario);
            return Optional.of(funcionarioMapper.toDto(funcionario));
        }
        return Optional.empty();
    }

    public void delete(UUID id) {
        funcionarioRepository.deleteById(id);
    }

}
