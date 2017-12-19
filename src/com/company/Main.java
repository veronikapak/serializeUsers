package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {
    private static final String FILENAME = "C:\\java\\Serialization\\users.txt";
    private static final String EXIT = "exit";

    public static void main(String[] args) {
        writeUser();
        System.out.println();
        //serialize();
        ArrayList<User> list = deserialize();
        for (User u:list) {
            System.out.println(u);
        }
    }

    private static void writeUser() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        User user = null;
        ArrayList<User> listUsers = new ArrayList<>();
        File file = new File(FILENAME);

        try {
            if(file.exists()) {
                listUsers = deserialize();
            }
            while (true) {
                System.out.println("If you want to quit enter exit.");

                System.out.println("Write a username: ");
                String name = reader.readLine();
                if(name.equalsIgnoreCase(EXIT)) break;

                System.out.println("Write a password: ");
                String password = reader.readLine();
                System.out.println("Write a firstname: ");
                String firstname = reader.readLine();
                System.out.println("Write a lastname: ");
                String lastname = reader.readLine();
                System.out.println("Write a email: ");
                String email = reader.readLine();

                user = new User(listUsers.size()+1, name, password, firstname, lastname, email);

                    if (listUsers.isEmpty()){
                        listUsers.add(user);
                    }else {
                        ArrayList<User> listTempo = new ArrayList<User>(listUsers) ;
                        boolean isExist=false;
                        for (User u : listTempo) {
                            if (name.equals(u.getUsername()))
                            {
                                System.out.println("This username is already exists. Try again.");
                                isExist=true;
                            }
                        }

                        if (!isExist){
                            listUsers.add(user);
                        }
                    }
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
}
