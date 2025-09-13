package io.github.pietroow.real_estate_monitoring.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "obra", schema = "real-estate-monitoring")
@Getter
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

}