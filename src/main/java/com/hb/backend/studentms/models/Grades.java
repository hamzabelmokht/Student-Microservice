package com.hb.backend.studentms.models;

import java.util.ArrayList;

public class Grades{
    public String studentNumber;
    public ArrayList<Grade> grades;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }
}
