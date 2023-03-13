package com.hb.backend.studentms.models;

import java.util.ArrayList;

public class Courses{
    public String studentNumber;
    public ArrayList<Course> courses;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}