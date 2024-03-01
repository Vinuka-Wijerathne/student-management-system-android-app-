package com.example.studentmanagementsystem;

public class ClassItem {
    String ClassName;
    String SubjectName;

    public ClassItem(String className, String subjectName) {
        ClassName = className;
        SubjectName = subjectName;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }
}
