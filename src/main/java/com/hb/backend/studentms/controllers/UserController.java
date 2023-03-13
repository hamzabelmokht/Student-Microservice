package com.hb.backend.studentms.controllers;

import com.hb.backend.studentms.models.Student;
import com.hb.backend.studentms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletionStage;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> validateUsername(@PathVariable("studentId") String studentId){
       Student student = userService.getStudent(studentId);
       return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @GetMapping("/student/nb/{studentId}")
    public CompletionStage<ResponseEntity<Student>> getStudent(@PathVariable("studentId") String studentId){
        return userService.getStudentNb(studentId).thenApply(student -> {
            return ResponseEntity.status(HttpStatus.OK).body(student);
        });

    }

}

