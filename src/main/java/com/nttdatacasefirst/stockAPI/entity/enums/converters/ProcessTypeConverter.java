package com.nttdatacasefirst.stockAPI.entity.enums.converters;

import com.nttdatacasefirst.stockAPI.entity.Process;
import com.nttdatacasefirst.stockAPI.entity.enums.ProcessType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = true)
public class ProcessTypeConverter implements AttributeConverter<ProcessType, Long> {
    @Override
    public Long convertToDatabaseColumn(ProcessType attribute) {
        if(attribute == null)
            return null;
        return attribute.getValue();
    }

    @Override
    public ProcessType convertToEntityAttribute(Long dbData) {
        if(dbData == null)
            return null;
        for(ProcessType process : ProcessType.values()){
            if(Objects.equals(process.getValue(), dbData))
                return process;
        }
        return null;
    }
}
