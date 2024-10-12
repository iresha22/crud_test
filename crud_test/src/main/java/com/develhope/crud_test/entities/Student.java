package com.develhope.crud_test.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Student {
    // parameters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private boolean isWorking;

    // empty constructor
    public Student(){}
    //constructor with all the parameters
    public Student(Long id, String name, String surname, boolean isWorking) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isWorking = isWorking;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean getIWorking() {
        return isWorking;
    }

    public void setIsWorking(boolean working) {
        this.isWorking = working;
    }
}
