package com.tushar;

import java.util.List;

public class Student {
    private int studentId;
    private String studentName;
    private String studentAddress;
    private List<String> subjects;

    public Student() {
        super();
    }

    public Student(int studentId, String studentName, String studentAddress, List<String> subjects) {
        System.out.println("calling constructor");
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.subjects = subjects;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        System.out.println("setting name");
        this.studentId = studentId;
    }

    public String getStudentName() {
        System.out.println("setting name");
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", student subjects'" + subjects + '\'' +
                '}';
    }

}
