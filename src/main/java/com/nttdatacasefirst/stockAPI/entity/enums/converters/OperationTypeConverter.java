package com.nttdatacasefirst.stockAPI.entity.enums.converters;

import com.nttdatacasefirst.stockAPI.entity.enums.OperationType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = true)
public class OperationTypeConverter implements AttributeConverter<OperationType, Long> {
    @Override
    public Long convertToDatabaseColumn(OperationType attribute) {
        if(attribute == null)
            return null;
        return attribute.getValue();
    }

    @Override
    public OperationType convertToEntityAttribute(Long dbData) {
        if(dbData == null)
            return null;
        for(OperationType process : OperationType.values()){
            if(Objects.equals(process.getValue(), dbData))
                return process;
        }
        return null;
    }
}
