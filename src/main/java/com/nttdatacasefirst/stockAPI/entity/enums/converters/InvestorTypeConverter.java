package com.nttdatacasefirst.stockAPI.entity.enums.converters;

import com.nttdatacasefirst.stockAPI.entity.enums.InvestorType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = true)
public class InvestorTypeConverter implements AttributeConverter<InvestorType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(InvestorType attribute) {
        if(attribute == null)
            return null;
        return attribute.getValue();
    }

    @Override
    public InvestorType convertToEntityAttribute(Integer dbData) {
        if(dbData == null)
            return null;
        for(InvestorType investor: InvestorType.values()){
            if(Objects.equals(investor.getValue(), dbData))
                return investor;
        }
        return null;
    }
}
