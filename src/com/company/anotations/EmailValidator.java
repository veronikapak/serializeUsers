package com.company.anotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    public static void validate(String str){

        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{1,3})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(str);
        if(!matcher.find()){
           throw new RuntimeException("Invalid email.");
        }
    }
}
