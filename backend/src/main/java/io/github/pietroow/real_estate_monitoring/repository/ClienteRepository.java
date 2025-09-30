package io.github.pietroow.real_estate_monitoring.repository;

import io.github.pietroow.real_estate_monitoring.model.Cliente;
import io.github.pietroow.real_estate_monitoring.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Cliente findByCpfCnpj(String cpfCnpj);

    public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    }
}
