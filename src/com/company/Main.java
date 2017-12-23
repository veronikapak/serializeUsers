package com.company;

import com.company.anotations.*;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Main {
    private static final String FILENAME = "C:\\java\\Serialization\\users.txt";
    private static final String EXIT = "exit";

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        writeUser();
        System.out.println();
        //serialize();
        ArrayList<User> list = deserialize();
        Field[] fields;
        for (User u:list) {
            System.out.println(u);
        }

    }

    private static void writeUser() throws NoSuchFieldException, IllegalAccessException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        User user; //null;
        ArrayList<User> listUsers = new ArrayList<>();
        File file = new File(FILENAME);
        Field[] fields;
        boolean flag = true;

        try {
            if(file.exists()) {
                listUsers = deserialize();
            }
            while (flag) {
                user = new User();
                System.out.println("If you want to quit enter exit.");
                fields = user.getClass().getDeclaredFields();
                String str ="";

                for (Field f:fields) {
                    f.setAccessible(true);
                    System.out.println(getFieldName(f));
                    //---------------System.out.println("Write a " + f.getName());
                    str = reader.readLine();
                    if(str.equals(EXIT)) {
                        flag=false;
                        break;
                    }
                    else {
                        f.set(user, str);
                        NotNullProcessing(f, user);
                        LengthProcessing(f, user);
                        IntegerProcessing(f, user);
                        EmailProcessing(f, user);
                    }
                    //-------------System.out.println(f.get(user));
                }
                if (flag){

                    ArrayList<User> listTempo = new ArrayList<User>(listUsers) ;
                    boolean isExist=false;
                    for (User u : listTempo) {
                        if (user.getUsername().equals(u.getUsername()))
                        {
                            System.out.println("This username is already exists. Try again.");
                            isExist=true;
                        }
                    }
                    if (!isExist){
                        listUsers.add(user);
                    }
                }

                /*System.out.println("Write a username: ");
                String name = reader.readLine();
                if(name.equalsIgnoreCase(EXIT)) break;
                System.out.println("Write a password: ");
                String password = reader.readLine();
                System.out.println("Write a firstname: ");
                String firstname = reader.readLine();
                System.out.println("Write a lastname: ");
                String lastname = reader.readLine();
                System.out.println("Write a email: ");
                String email = reader.readLine();*/


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        serialize(listUsers);

    }

    private static void serialize(ArrayList<User> list){
        try {
            FileOutputStream fileOut = new FileOutputStream(new File(FILENAME));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static ArrayList<User> deserialize(){
        ArrayList<User> list = new ArrayList<>();
        User user =null;
        list.add(user);
        try {
            FileInputStream fileIn = new FileInputStream(new File(FILENAME));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (ArrayList<User>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException c){
            System.out.println("User class not found");
            c.printStackTrace();
            return null;
        }
        return list;
    }

    private static void NotNullProcessing(Field field, Object user) throws IllegalAccessException {
        if(field.getAnnotation(NotNull.class)!=null){
            field.setAccessible(true);
            Object o = field.get(user);
            NotNullValidator.validate(o);
        }
    }

    private static void LengthProcessing(Field field, Object user) throws IllegalAccessException {
        if(field.getAnnotation(LengthString.class)!=null){
            //field.setAccessible(true);
            LengthString lengthString = field.getAnnotation(LengthString.class);
            Object o = field.get(user);
            LengthValidator.validate((String) o, lengthString.minValue(), lengthString.maxValue());
        }
    }

    private static void IntegerProcessing(Field field, Object user) throws IllegalAccessException {
        if(field.getAnnotation(LengthInteger.class)!=null){
            LengthInteger lengthInteger = field.getAnnotation(LengthInteger.class);
            Object o = field.get(user);
            IntegerValidator.validation((String) o, lengthInteger.minValue(), lengthInteger.maxValue());
        }
    }

    private static String getFieldName(Field field){
        if(field.getAnnotation(PrintAnnotation.class)!=null){
            PrintAnnotation annotation = field.getAnnotation(PrintAnnotation.class);
            return annotation.printValue();
        }
        return field.getName();
    }

    private static void EmailProcessing(Field field, Object user) throws IllegalAccessException {
        if(field.getAnnotation(Email.class)!=null){
            field.setAccessible(true);
            Object o = field.get(user);
            EmailValidator.validate((String) o);
        }
    }

}
