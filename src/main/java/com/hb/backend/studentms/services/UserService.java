package com.hb.backend.studentms.services;

import com.hb.backend.studentms.config.ApplicationConfig;
import com.hb.backend.studentms.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ApplicationConfig config;
    public Student getStudent(String studentId){
        String url = String.format(config.account, studentId);
        Account studentAccount = restTemplate.getForObject(config.account,Account.class);
        Grades studentGrades = restTemplate.getForObject(config.grades, Grades.class);
        StudentAddress studentAddress = restTemplate.getForObject(config.address, StudentAddress.class);
        Courses studentCourses = restTemplate.getForObject(config.courses, Courses.class);
        Student student = new Student();
        student.setStudentNumber(studentId);
        student.setFirstName(studentAccount.getFirstName());
        student.setLastName(studentAccount.getLastName());
        student.setPhoneNumber(studentAccount.getPhoneNumber());
        student.setGrades(studentGrades.getGrades());
        student.setAddress(studentAddress.getAddress());
        student.setCourses(studentCourses.getCourses());

        return student;
    }


    public CompletionStage<Student> getStudentNb(String studentId){
        String url = String.format(config.account, studentId);
        CompletableFuture<Account> studentAccountFuture = CompletableFuture.supplyAsync(()->restTemplate.getForObject(config.account,Account.class));
        CompletableFuture<Grades> studentGradesFuture = CompletableFuture.supplyAsync(()->restTemplate.getForObject(config.grades, Grades.class));
        CompletableFuture<StudentAddress> studentAddressFuture = CompletableFuture.supplyAsync(()->restTemplate.getForObject(config.address, StudentAddress.class));
        CompletableFuture<Courses> studentCoursesFuture = CompletableFuture.supplyAsync(()->restTemplate.getForObject(config.courses, Courses.class));
        CompletableFuture allResponses = CompletableFuture.allOf(studentAccountFuture, studentGradesFuture, studentAddressFuture, studentCoursesFuture);
        return allResponses.thenApply(v->{
            Account studentAccount = studentAccountFuture.join();
            Grades  studentGrades = studentGradesFuture.join();
            StudentAddress studentAddress = studentAddressFuture.join();
            Courses studentCourses = studentCoursesFuture.join();

            Student student = new Student();
            student.setStudentNumber(studentId);
            student.setFirstName(studentAccount.getFirstName());
            student.setLastName(studentAccount.getLastName());
            student.setPhoneNumber(studentAccount.getPhoneNumber());
            student.setGrades(studentGrades.getGrades());
            student.setAddress(studentAddress.getAddress());
            student.setCourses(studentCourses.getCourses());

            return student;

        });


    }


}
