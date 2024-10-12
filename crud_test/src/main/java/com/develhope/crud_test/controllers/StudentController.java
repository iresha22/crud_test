package com.develhope.crud_test.controllers;

import com.develhope.crud_test.entities.Student;
import com.develhope.crud_test.repositories.StudentRepository;
import com.develhope.crud_test.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Students")
public class StudentController {
    // parameters
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    // POSTMAPPING create new student
    @PostMapping("/newstudent")
    public ResponseEntity<Student> createNewUser(@RequestBody Student student){
        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    // method to update student
    @PutMapping("/new/{id}")
    public ResponseEntity<Student>  updateStudent(@PathVariable Long id, @RequestBody Student student){
       studentService.updateStudent(id,student);
         return ResponseEntity.ok(student);
    }

    // method to update isWorking parameter
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestParam boolean isWorking){
        Optional<Student> updatedStudent = studentService.updateIsWoking(id,isWorking);
        Student student = updatedStudent.get();
        return ResponseEntity.ok(student);
    }


    // GET one student
    @GetMapping("/{id}")
    public ResponseEntity<Void> returnLibro(@PathVariable Long id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
   // student list
    @GetMapping("/studentlist")
    public ResponseEntity<List<Student>> studentlist(){
        return ResponseEntity.ok(studentRepository.findAll());
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnLibro(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
