package io.github.pietroow.real_estate_monitoring.dto;

import io.github.pietroow.real_estate_monitoring.model.TipoPessoa;
import java.util.UUID;


public record ClienteResponseDTO(

        UUID id,

        String nome,

        String razaoSocial,

        String cpfCnpj,

        TipoPessoa tipo,

        String inscricaoEstadual,

        String inscricaoMunicipal,

        String telefone1,
        
        String telefone2,

        String email,

        String comentario,

        EnderecoResponseDTO endereco
){}
