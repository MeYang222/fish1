package com.student.domain;

import lombok.Data;
/**
 * @author aloneAtri
 * @version 1.0
 * @description 描述信息
 * @date 2023/5/31 0031 18:24
 */
@Data
public class Student {
    private String id;
    private String name;
    private String sex;
    private String age;

    private Double javascore;
    private Double gaoshuscore;
    private Double englishscore;
    private Double total;

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
