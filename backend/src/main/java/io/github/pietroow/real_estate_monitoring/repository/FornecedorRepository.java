package io.github.pietroow.real_estate_monitoring.repository;

import io.github.pietroow.real_estate_monitoring.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID>, JpaSpecificationExecutor<Fornecedor> {

    boolean existsByCnpj(String cnpj);

}
