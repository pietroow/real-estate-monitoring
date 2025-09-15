package io.github.pietroow.real_estate_monitoring.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {

    String cep;
    String endereco;
    short numero;
    String complemento;
    String bairro;
    String estado;
    String cidade;

    }
