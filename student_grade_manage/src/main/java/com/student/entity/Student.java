package com.student.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Student extends ArrayList<Student> {
    private String id;
    private String name;
    private String sex;
    private String age;

    private double javascore;
    private double gaoshuscore;
    private double englishscore;
    private double total;

    public Student() {
    }

    public Student(String id, String name, String sex, String age, double javascore, double gaoshuscore, double englishscore) {
        this.id = id;
        setName(name);
        this.sex = sex;
        setAge(age);
        this.javascore = javascore;
        this.gaoshuscore = gaoshuscore;
        this.englishscore = englishscore;

    }

    public double getTotal() {
        return javascore+gaoshuscore+englishscore;
    }


}
