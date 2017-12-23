package com.company.anotations;

public class NotNullValidator {

    public static void validate(Object o){
        if(o.equals(null) || o.equals("")){
            throw new RuntimeException("Field is null");
        }

        String str = (String) o;
        if(str.replaceAll("\\s+","").equals("")){
            throw new RuntimeException("Field is null");
        }

    }
    /*public static void validate(String s){
        if(s.equals(null) || s.equals("")){
            throw new RuntimeException("Field is null");
        }
    }*/
}
