package com.bryansinqadu.eparking_app;

public class User {

    private String Name;
    private String Phone;
    private String Gender;
    private String Email;
    private String Password;

    public User() {
    }

    public User(String name, String phone, String gender, String email, String password) {
        Name = name;
        Phone = phone;
        Gender = gender;
        Email = email;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}