package io.github.pietroow.real_estate_monitoring.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Endereco {

    String cep;
    String endereco;
    String numero;
    String complemento;
    String bairro;
    String estado;
    String cidade;

    }
