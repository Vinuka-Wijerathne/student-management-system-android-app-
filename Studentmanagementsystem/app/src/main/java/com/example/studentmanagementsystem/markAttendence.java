package com.example.studentmanagementsystem;

public class markAttendence {

    private String ID;
    private String Name;
    private String Date;
    private String Mark;

    public markAttendence(){}
    public markAttendence(String ID, String name, String date, String mark) {
        this.ID = ID;
        Name = name;
        Date = date;
        Mark = mark;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMark() {
        return Mark;
    }

    public void setMark(String mark) {
        Mark = mark;
    }
}
