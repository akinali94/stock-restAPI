package com.nttdatacasefirst.stockAPI.config;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CustomIdGenerator implements IdentifierGenerator {
    private static final Map<String, Boolean> generatedId = new HashMap<>();

    private String generateRandomId(){
        Random random = new Random();
        long randomNumber = random.nextLong(10000000,90000000);
        return Long.toString(randomNumber);
    }

    private static boolean isIdUnique(String id){
        return !generatedId.containsKey(id);
    }

    private static void addId(String id){
        generatedId.put(id, true);
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object o)
            throws HibernateException {

        String id;

        do{
            id = generateRandomId();

        } while(!isIdUnique(id));

        addId(id);
        return Long.parseLong(id);
    }
}
