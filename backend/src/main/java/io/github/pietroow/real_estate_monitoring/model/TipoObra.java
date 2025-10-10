package io.github.pietroow.real_estate_monitoring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tipo_obra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoObra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome")
    private String nome;
}
