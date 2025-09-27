package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.FuncionarioRequestDTO;
import io.github.pietroow.real_estate_monitoring.dto.FuncionarioResponseDTO;
import io.github.pietroow.real_estate_monitoring.model.Funcionario;
import io.github.pietroow.real_estate_monitoring.repository.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioResponseDTO create(FuncionarioRequestDTO requestDTO) {
        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(requestDTO, funcionario);
        funcionario = funcionarioRepository.save(funcionario);

        FuncionarioResponseDTO responseDTO = new FuncionarioResponseDTO();
        BeanUtils.copyProperties(funcionario, responseDTO);
        return responseDTO;
    }

    public Page<FuncionarioResponseDTO> findAll(Pageable pageable) {
        Page<Funcionario> funcionariosPage = funcionarioRepository.findAll(pageable);
        return funcionariosPage.map(funcionario -> {
            FuncionarioResponseDTO responseDTO = new FuncionarioResponseDTO();
            BeanUtils.copyProperties(funcionario, responseDTO);
            return responseDTO;
        });
    }

    public Optional<FuncionarioResponseDTO> findById(UUID id) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
        return optionalFuncionario.map(funcionario -> {
            FuncionarioResponseDTO responseDTO = new FuncionarioResponseDTO();
            BeanUtils.copyProperties(funcionario, responseDTO);
            return responseDTO;
        });
    }

    public Optional<FuncionarioResponseDTO> update(UUID id, FuncionarioRequestDTO requestDTO) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
        if (optionalFuncionario.isPresent()) {
            Funcionario funcionario = optionalFuncionario.get();
            BeanUtils.copyProperties(requestDTO, funcionario);
            funcionario = funcionarioRepository.save(funcionario);

            FuncionarioResponseDTO responseDTO = new FuncionarioResponseDTO();
            BeanUtils.copyProperties(funcionario, responseDTO);
            return Optional.of(responseDTO);
        }
        return Optional.empty();
    }

    public void delete(UUID id) {
        funcionarioRepository.deleteById(id);
    }

}
