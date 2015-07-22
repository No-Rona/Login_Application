package com.tinyowl.rohan.login_application;


public class Users {

    private int mId;
    private String mName;
    private String mPassword;
    private int mImage;


    public Users(int id, String name, String pass){
        this.mId = id;
        this.mName = name;
        this.mPassword = pass;
        this.mImage = R.drawable.icon;
    }

    public Users(String name, String pass) {
        this.mName = name;
        this.mPassword = pass;
        this.mImage = R.drawable.icon;
    }


    public int getID(){  return this.mId;  }


    public void setID(int id){ this.mId = id; }


    public String getName(){
        return this.mName;
    }

    public int getImage() { return this.mImage; }

    public void setImage(int imgId) { this.mImage = imgId; }

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