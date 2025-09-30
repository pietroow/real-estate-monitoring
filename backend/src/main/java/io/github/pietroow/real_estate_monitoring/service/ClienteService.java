package io.github.pietroow.real_estate_monitoring.service;

import io.github.pietroow.real_estate_monitoring.dto.ClienteRequestDTO;
import io.github.pietroow.real_estate_monitoring.model.Cliente;
import io.github.pietroow.real_estate_monitoring.model.Endereco;
import io.github.pietroow.real_estate_monitoring.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @Transactional
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public void deletar(UUID id) {
        clienteRepository.deleteById(id);
    }

    @Transactional
    public Cliente atualizar(UUID id, ClienteRequestDTO dto) {
        Cliente existente = buscarPorId(id);
        existente.setNome(dto.nome());
        existente.setRazaoSocial(dto.razaoSocial());
        existente.setTipo(dto.tipo());
        existente.setCpfCnpj(dto.cpfCnpj());
        existente.setInscricaoEstadual(dto.inscricaoEstadual());
        existente.setInscricaoMunicipal(dto.inscricaoMunicipal());
        existente.setTelefone1(dto.telefone1());
        existente.setTelefone2(dto.telefone2());
        existente.setEmail(dto.email());
        existente.setComentario(dto.comentario());

        if (dto.endereco() != null) {
            Endereco end = existente.getEndereco();
            if (end == null) {
                end = new Endereco();
                existente.setEndereco(end);
            }
            end.setCep(dto.endereco().cep());
            end.setEndereco(dto.endereco().logradouro());
            end.setNumero(dto.endereco().numero());
            end.setComplemento(dto.endereco().complemento());
            end.setBairro(dto.endereco().bairro());
            end.setEstado(dto.endereco().estado());
            end.setCidade(dto.endereco().cidade());
        }
        return clienteRepository.save(existente);
    }
}
