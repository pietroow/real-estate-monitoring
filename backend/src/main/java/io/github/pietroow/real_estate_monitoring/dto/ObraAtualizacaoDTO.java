package io.github.pietroow.real_estate_monitoring.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ObraAtualizacaoDTO {
    private String nome;
    private String codigo;
    private String art;
    private String responsavelTecnico;
    private String responsavelDaObra;
    private String ceiCno;
    private BigDecimal areaTotal;
    private String comentario;
    private boolean statusParaLancamentos;
    private boolean statusParaFaturamentos;
    private boolean statusParaCompras;
    private Integer tipoId;
    private Integer statusId;
    private Integer unidadeId;

}
