package io.github.pietroow.real_estate_monitoring.repository;

import io.github.pietroow.real_estate_monitoring.model.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {
    boolean existsByCnpj(String cnpj);

    @Query("""
            select f
              from Fornecedor f
             where (:cnpj is null or f.cnpj = :cnpj)
               and (:razaoSocial is null or lower(f.razaoSocial) like lower(concat('%', :razaoSocial, '%')))
               and (:nomeFantasia is null or lower(f.nomeFantasia) like lower(concat('%', :nomeFantasia, '%')))
               and (:cidade is null or lower(f.cidade) like lower(concat('%', :cidade, '%')))
               and (:uf is null or f.uf = :uf)
               and (:status is null or f.status = :status)
            """)
    Page<Fornecedor> search(String cnpj,
                            String razaoSocial,
                            String nomeFantasia,
                            String cidade,
                            String uf,
                            io.github.pietroow.real_estate_monitoring.model.enums.FornecedorStatus status,
                            Pageable pageable);
}