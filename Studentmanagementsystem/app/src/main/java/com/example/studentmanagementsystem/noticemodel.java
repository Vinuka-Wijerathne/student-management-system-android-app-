package com.example.studentmanagementsystem;

public class noticemodel {

    private String date;
    private String Message;

    public noticemodel(String date, String message) {
        this.date = date;
        Message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
