package com.example.testreadexcel.service;

import com.example.testreadexcel.entities.Students;
import com.example.testreadexcel.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Students save(Students students) {
        return studentRepository.save(students);
    }
}
