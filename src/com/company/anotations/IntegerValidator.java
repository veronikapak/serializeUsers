package com.company.anotations;

public class IntegerValidator {
    public static void validation(String str, int min, int max){
        try {
            Integer i = Integer.parseInt(str);
            if(i!=0){
                if((str.length()>=min && str.length()<=max)) {
                    return;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Field must be a number");
        }
        throw new RuntimeException("Field must be number and between " + min + " and " + max);

    }
}
