package io.github.pietroow.real_estate_monitoring.repository;

import io.github.pietroow.real_estate_monitoring.model.UnidadeMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Integer> {
}
