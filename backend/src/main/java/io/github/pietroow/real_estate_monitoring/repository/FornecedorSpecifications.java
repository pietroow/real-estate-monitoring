package io.github.pietroow.real_estate_monitoring.repository;

import io.github.pietroow.real_estate_monitoring.dto.FornecedorFilterDto;
import io.github.pietroow.real_estate_monitoring.model.Fornecedor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public final class FornecedorSpecifications {
    private FornecedorSpecifications() {
    }

    public static Specification<Fornecedor> byFilter(FornecedorFilterDto f) {
        return (root, q, cb) -> {
            var p = cb.conjunction();
            if (f == null) return p;
            if (StringUtils.hasText(f.cnpj()))
                p.getExpressions().add(cb.equal(root.get("cnpj"), f.cnpj()));
            if (StringUtils.hasText(f.razaoSocial()))
                p.getExpressions().add(cb.like(cb.lower(root.get("razaoSocial")), "%" + f.razaoSocial().toLowerCase() + "%"));
            if (StringUtils.hasText(f.nomeFantasia()))
                p.getExpressions().add(cb.like(cb.lower(root.get("nomeFantasia")), "%" + f.nomeFantasia().toLowerCase() + "%"));
            if (f.status() != null)
                p.getExpressions().add(cb.equal(root.get("status"), f.status()));
            if (StringUtils.hasText(f.cidade()))
                p.getExpressions().add(cb.like(cb.lower(root.get("cidade")), "%" + f.cidade().toLowerCase() + "%"));
            if (StringUtils.hasText(f.uf()))
                p.getExpressions().add(cb.equal(root.get("uf"), f.uf()));
            return p;
        };
    }
}
