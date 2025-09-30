package io.github.pietroow.real_estate_monitoring.repository;

import io.github.pietroow.real_estate_monitoring.model.TipoObra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoObraRepository extends JpaRepository<TipoObra, Integer> {
    Optional<TipoObra> findByNome(String nome);
}
