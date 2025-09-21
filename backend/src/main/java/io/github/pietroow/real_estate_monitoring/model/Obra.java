package io.github.pietroow.real_estate_monitoring.model;

import io.github.pietroow.real_estate_monitoring.model.Enum.StatusObra;
import io.github.pietroow.real_estate_monitoring.model.Enum.TipoObra;
import io.github.pietroow.real_estate_monitoring.model.Enum.UnidadeMedida;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "obra", schema = "real-estate-monitoring")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    public UUID id;

    @Column(name = "nome_obra")
    public String nomeObra;

    @Column(name = "codigo_obra")
    public String codigoObra;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_obra")
    public TipoObra tipoObra;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_obra")
    private StatusObra statusObra;

    @Column(name = "art", unique = true)
    public String art;

    @Column(name = "responsavel_tecnico")
    private String responsávelTecnico;

    @Column(name = "responsavel_da_obra")
    private String responsavelDaObra;

    @Column(name = "cei_cno")
    private String ceiCno;

    @Column(name = "area_total", precision = 10, scale = 2)
    private BigDecimal areaTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_medida")
    private UnidadeMedida unidade;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "obra_visivel_para")
    private String obraVisivelPara; //VERIFICAR C/ PIETRO

    @Column(name = "status_lancamentos")
    private boolean statusParaLancamentos;

    @Column(name = "status_faturamentos")
    private boolean statusParaFaturamentos;

    @Column(name = "status_compras")
    private boolean statusParaCompras;

}
