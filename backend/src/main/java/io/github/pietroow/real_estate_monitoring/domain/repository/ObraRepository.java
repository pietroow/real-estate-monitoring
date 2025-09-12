package io.github.pietroow.real_estate_monitoring.domain.repository;

import io.github.pietroow.real_estate_monitoring.domain.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {

}
