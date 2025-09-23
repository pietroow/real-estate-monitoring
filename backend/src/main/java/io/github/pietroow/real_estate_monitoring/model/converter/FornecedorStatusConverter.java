package io.github.pietroow.real_estate_monitoring.model.converter;

import io.github.pietroow.real_estate_monitoring.model.enums.FornecedorStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FornecedorStatusConverter implements AttributeConverter<FornecedorStatus, String> {

    @Override
    public String convertToDatabaseColumn(FornecedorStatus status) {
        if (status == null) return null;
        return switch (status) {
            case ATIVO -> "Ativo";
            case INATIVO -> "Inativo";
        };
    }

    @Override
    public FornecedorStatus convertToEntityAttribute(String db) {
        if (db == null) return null;
        return switch (db) {
            case "Ativo" -> FornecedorStatus.ATIVO;
            case "Inativo" -> FornecedorStatus.INATIVO;
            default -> throw new IllegalArgumentException("Status inválido: " + db);
        };
    }
}
