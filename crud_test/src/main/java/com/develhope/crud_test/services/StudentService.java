package com.develhope.crud_test.services;

import com.develhope.crud_test.entities.Student;
import com.develhope.crud_test.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    // parameters
    @Autowired
    private StudentRepository studentRepository;

    // method to change the value of isWorking
    public Optional<Student> updateIsWoking(Long id, boolean isWorking){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            Student updateStudent = studentOptional.get();
            updateStudent.setIsWorking(isWorking);
            return Optional.of(studentRepository.save(updateStudent));
        }
        return Optional.empty();
    }


    public Optional<Student> updateStudent(Long id, Student student){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            Student updatedStudent = studentOptional.get();
            updatedStudent.setName(student.getName());
            updatedStudent.setSurname(student.getSurname());
            updatedStudent.setIsWorking(student.getIWorking());
            return Optional.of(studentRepository.save(updatedStudent));
        }
        return Optional.empty();
    }
}
