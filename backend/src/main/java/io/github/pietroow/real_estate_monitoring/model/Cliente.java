package io.github.pietroow.real_estate_monitoring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //Informações de Pessoa Jurídica
    @Column(name="nome")
    private String nome;

    @NotNull
    @Column(name="razao_social")
    private String razaoSocial;


    @Enumerated(EnumType.STRING)
    @Column(name="tipo")
    private TipoPessoa tipo;

    @Column(name="cpf_cnpj",unique = true)
    private String cpfCnpj;

    @Column(name="inscricao_estadual")
    private String inscricaoEstadual;

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
    @ManyToOne
    Endereco endereco;
}
