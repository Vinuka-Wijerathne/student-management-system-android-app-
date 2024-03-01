package com.example.studentmanagementsystem;

public class Students {

    String ID;
    String Name;
    String Age;
    String School;
    String Telephone;
    String Course;
    String Address;
    String Username;
    String Password;

    public Students(){}

    public Students(String name, String age, String school, String telephone,String Address , String course , String Username ,  String Password) {

        Name = name;
        Age = age;
        School = school;
        Telephone = telephone;
        this.Address = Address;
        Course = course;
        this.Username =Username;
        this.Password = Password;

    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getCourse() {
        return Course;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setCourse(String course) {
        Course = course;
    }
}
