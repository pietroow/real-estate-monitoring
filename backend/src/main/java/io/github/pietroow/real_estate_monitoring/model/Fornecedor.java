package io.github.pietroow.real_estate_monitoring.model;

import io.github.pietroow.real_estate_monitoring.model.converter.FornecedorStatusConverter;
import io.github.pietroow.real_estate_monitoring.model.enums.FornecedorStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "fornecedor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "cnpj", nullable = false, unique = true, length = 18)
    private String cnpj;

    @Column(name = "razao_social", nullable = false, length = 255)
    private String razaoSocial;

    @Column(name = "nome_fantasia", length = 255)
    private String nomeFantasia;

    @Column(name = "inscricao_estadual", length = 20)
    private String inscricaoEstadual;

    @Column(name = "inscricao_municipal", length = 20)
    private String inscricaoMunicipal;

    @Column(name = "telefone_principal", length = 20)
    private String telefonePrincipal;

    @Column(name = "email_principal", nullable = false, length = 100)
    private String emailPrincipal;

    @Column(name = "nome_contato", length = 100)
    private String nomeContato;

    @Column(name = "cep", length = 9)
    private String cep;

    @Column(name = "logradouro", length = 255)
    private String logradouro;

    @Column(name = "numero", length = 20)
    private String numero;

    @Column(name = "complemento", length = 100)
    private String complemento;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "cidade", length = 100)
    private String cidade;

    @Column(name = "uf", length = 2)
    private String uf;

    @Column(name = "banco_codigo", length = 10)
    private String bancoCodigo;

    @Column(name = "agencia", length = 10)
    private String agencia;

    @Column(name = "conta", length = 20)
    private String conta;

    @Column(name = "pix", length = 255)
    private String pix;

    @Convert(converter = FornecedorStatusConverter.class)
    @Column(name = "status", nullable = false, length = 20)
    private FornecedorStatus status = FornecedorStatus.ATIVO;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        var now = LocalDateTime.now();
        this.dataCadastro = now;
        this.dataAtualizacao = now;
        if (this.status == null) this.status = FornecedorStatus.ATIVO;
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
