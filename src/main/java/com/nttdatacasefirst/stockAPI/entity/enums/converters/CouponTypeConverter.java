package com.nttdatacasefirst.stockAPI.entity.enums.converters;

import com.nttdatacasefirst.stockAPI.entity.enums.CouponType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = true)
public class CouponTypeConverter implements AttributeConverter<CouponType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CouponType attribute) {
        if(attribute == null ){
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public CouponType convertToEntityAttribute(Integer dbData) {
        if(dbData == null)
            return null;
        for(CouponType cType : CouponType.values()){
            if(Objects.equals(cType.getValue(), dbData))
                return cType;
        }
        return null;
    }
}
