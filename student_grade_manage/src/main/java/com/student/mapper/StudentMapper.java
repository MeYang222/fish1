package com.student.mapper;

import com.student.domain.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author aloneAtri
 * @version 1.0
 * @description 描述信息
 * @date 2023/5/31 0031 17:23
 */
public interface StudentMapper {
    List<Student> selectAllStudent();

    Student getStudentByName(String name);

    Student getStudentById(String id);

    void deleteStudentById(String id);

    void updateStudent(Student student);

    void addStudent(Student student);

    @Select("select AVG(javascore) from tb_student;")
    double getJavaScoreAvg();
    @Select("select AVG(gaoshuscore) from tb_student;")
    double getGaoshuScoreAvg();
    @Select("select AVG(englishscore) from tb_student;")
    double getEnglishScoreAvg();

    @Select("select MAX(javascore) from tb_student;")
    double getJavaMax();
    @Select("select MAX(gaoshuscore) from tb_student;")
    double getGaoshuMax();
    @Select("select MAX(englishscore) from tb_student;")
    double getEnglishMax();

    @Select("select MIN(javascore) from tb_student;")
    double getJavaMin();
    @Select("select MIN(gaoshuscore) from tb_student;")
    double getGaoshuMin();
    @Select("select MIN(englishscore) from tb_student;")
    double getEnglishMin();

    @Select("select  *,(javascore+englishscore+gaoshuscore) as sum from tb_student order by sum desc ;")
    List<Student> descByTotal();
}
