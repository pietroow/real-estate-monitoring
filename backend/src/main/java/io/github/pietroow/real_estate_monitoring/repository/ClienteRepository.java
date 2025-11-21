package io.github.pietroow.real_estate_monitoring.repository;

import io.github.pietroow.real_estate_monitoring.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    boolean existsByCpfOrCnpjAndIdNot(String cnpj, String cpf, UUID id);

    boolean existsByCpfOrCnpj(String cpf, String cnpj);
}
