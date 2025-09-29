package io.github.pietroow.real_estate_monitoring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "obra")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    public UUID id;

    @Column(name = "nome")
    public String nome;

    @Column(name = "codigo")
    public String codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    public TipoObra tipoObra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private StatusObra status;

    @Column(name = "art")
    public String art;

    @Column(name = "responsavel_tecnico")
    private String responsavelTecnico;

    @Column(name = "responsavel_obra")
    private String responsavelDaObra;

    @Column(name = "cei_cno")
    private String ceiCno;

    @Column(name = "area_total", precision = 10, scale = 2)
    private BigDecimal areaTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_id")
    private UnidadeMedida unidade;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "status_lancamentos")
    private boolean statusParaLancamentos;

    @Column(name = "status_faturamentos")
    private boolean statusParaFaturamentos;

    @Column(name = "status_compras")
    private boolean statusParaCompras;

}
