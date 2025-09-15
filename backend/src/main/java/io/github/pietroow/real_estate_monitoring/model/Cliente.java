package io.github.pietroow.real_estate_monitoring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cliente")
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //Informações de Pessoa Jurídica
    @NotBlank
    @Column(name="nome_fantasia")
    private String nome_fantasia;

    @NotBlank
    @Column(name="razao_social")
    private String razao_social;


    @Enumerated(EnumType.STRING)
    @Column(name="tipo")
    private static Tipo tipo;

    @NotBlank
    @Column(name="cpf_cnpj",unique = true)
    private String cpf_cnpj;

    @Column(name="inscricao_estadual")
    private String inscricao_estadual;

    @Column(name="inscricao_municipal")
    private String inscricaoMunicipal;

    @Column(name="telefone_1")
    private String telefone1;

    @Column(name="telefone_2")
    private String telefone2;

    @Column(name="email")
    private String email;

    @Column(columnDefinition = "TEXT")
    private String comentario;


    //Comum entre PF e PJ
    @Embedded
    Endereco endereco;
}
