package com.company;

import com.company.anotations.*;

import java.io.Serializable;

/**
 * Created by Gran1 on 17/12/2017.
 */
public class User implements Serializable{
    //private int id;
    @NotNull
    @LengthString(minValue = 1, maxValue = 15)
    @PrintAnnotation(printValue = "User name:")
    private String username;
    @LengthInteger(minValue = 1, maxValue = 4)
    private String password;
    private String firstname;
    private String lastname;
    @Email
    private String email;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(/*int id,*/ String username, String password, String firstname, String lastname, String email) {
        //this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                //"id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
