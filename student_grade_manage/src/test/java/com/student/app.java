package com.student;

import com.student.service.Impl.UserServiceImpl;
import org.junit.Test;

/**
 * @author aloneAtri
 * @version 1.0
 * @description 描述信息
 * @date 2023/6/1 0001 20:15
 */
public class app {
    @Test
    public void test() {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        System.out.println( userServiceImpl.getAllUser());
//        StudentService studentService = new StudentService();
//        Student student = new Student();
//        student.setId("1005");
//        student.setName("王源");
//        student.setJavascore(85.0);
//        student.setEnglishscore(80.0);
//        student.setGaoshuscore(90.0);
//        student.setAge("32");
//        student.setSex("男");
//        studentService.updateStudent(student);
//        System.out.println(studentService.descByTotal());
    }
}