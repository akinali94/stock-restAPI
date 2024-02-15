package com.nttdatacasefirst.stockAPI.entity.enums.converters;

import com.nttdatacasefirst.stockAPI.entity.enums.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Long> {
    @Override
    public Long convertToDatabaseColumn(Role attribute) {
        if(attribute == null)
            return null;
        return attribute.getValue() ;
    }

    @Override
    public Role convertToEntityAttribute(Long dbData) {
        if(dbData == null)
            return null;
        for(Role process: Role.values()){
            if(Objects.equals(process.getValue(), dbData))
                return null;
        }
        return null;
    }
}
