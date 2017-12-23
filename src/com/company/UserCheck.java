package com.company;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class UserCheck {

    private static final String FILENAME = "C:\\java\\Serialization\\users.txt";

    public static void main(String[] args) {
        User user = writeUsername();
        ArrayList<User> listUser = getUser();
        checkUser(listUser, user);
        try {
            Field field = user.getClass().getDeclaredField("username");
            System.out.println(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void checkUser(ArrayList<User> listUser, User user) {
        File file = new File(FILENAME);
        if (!file.exists()){
            System.out.println("This user is not exists.");
        }

        boolean check = false;
        for (User u:listUser) {
            if(u.getUsername().equalsIgnoreCase(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                System.out.println(u);
                check = true;
            }
        }
        if (!check) {
            System.out.println("This user is not exists.");
        }
    }

    private static ArrayList<User> getUser() {
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

    private static User writeUsername() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String username = "";
        String password = "";

        try {
            System.out.println("Write username:");
            username = bufferedReader.readLine();
            System.out.println("Write password:");
            password = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

            try {
                if(bufferedReader!=null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return new User( username, password, null, null, null);
    }


}
