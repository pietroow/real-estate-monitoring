package io.github.pietroow.real_estate_monitoring.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "obra", schema = "real-estate-monitoring")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;
}
