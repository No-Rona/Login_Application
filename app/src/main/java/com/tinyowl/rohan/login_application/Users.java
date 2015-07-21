package com.tinyowl.rohan.login_application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Users {

    int mId;
    String mName;
    String mPassword;


    public Users(int id, String name, String pass){
        this.mId = id;
        this.mName = name;
        this.mPassword = pass;
    }

    public Users(String name, String pass) {
        this.mName = name;
        this.mPassword = pass;
    }


    public int getID(){
        return this.mId;
    }


    public void setID(int id){
        this.mId = id;
    }


    public String getName(){
        return this.mName;
    }


    public void setName(String name){
        this.mName = name;
    }


    public String getPassword(){
        return this.mPassword;
    }


    public void setPassword(String pass){
        this.mPassword = pass;
    }
}