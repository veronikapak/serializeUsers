package com.company.anotations;

public class LengthValidator {

    public static void validate(String str, int min, int max){
        if(str.equals(null)){
            throw new RuntimeException("Value is null");
        }
        if(str.length()<=max && str.length()>=min){
            return;
        }
        throw new RuntimeException("Length is not valid. It might be between "+ min +" and " +max);
    }
}
