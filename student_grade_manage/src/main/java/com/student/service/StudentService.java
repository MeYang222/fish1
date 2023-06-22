package com.student.service;

import com.student.domain.Student;
import com.student.mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author aloneAtri
 * @version 1.0
 * @description 描述信息
 * @date 2023/6/9 0009 13:24
 */
public interface StudentService {

    /**
     * @description: 通过姓名查找学生
     * @param: name
     * @return: com.student.domain.Student
     * @date: 2023/6/9 0009 14:07
     */
    Student getStudentByName(String name);

    /**
     * @description: 通过id查找学生
     * @param: id
     * @return: com.student.domain.Student
     * @date: 2023/6/9 0009 14:08
     */
    Student getStudentById(String id);

    /**
     * @description: 获取所有学生
     * @param:
     * @return: java.util.List<com.student.domain.Student>
     * @date: 2023/6/9 0009 14:08
     */
    List<Student> getAllStudent();

    /**
     * @description: 获取java平均分
     * @param:
     * @return: double
     * @date: 2023/6/9 0009 14:08
     */
    double getJavaScoreAvg();

    /**
     * @description: 获取高数平均成绩
     * @param:
     * @return: double
     * @date: 2023/6/9 0009 14:09
     */
    double getGaoshuScoreAvg();

    /**
     * @description: 获取英语平均分
     * @param:
     * @return: double
     * @date: 2023/6/9 0009 14:09
     */
    double getEnglishScoreAvg();

    /**
     * @description: 获取java最高分
     * @param:
     * @return: double
     * @date: 2023/6/9 0009 14:09
     */
    double getJavaMax();

    /**
     * @description: 获取高数最高分
     * @param:
     * @return: double
     * @date: 2023/6/9 0009 14:10
     */
    double getGaoshuMax();

    /**
     * @description: 获取英语最高分
     * @param:
     * @return: double
     * @date: 2023/6/9 0009 14:10
     */
    double getEnglishMax();

    /**
     * @description: 获取java最高分
     * @param:
     * @return: double
     * @date: 2023/6/9 0009 14:05
     */
    double getJavaMin();

    /**
     * @description: 获取高数最低分
     * @param:
     * @return: double
     * @date: 2023/6/9 0009 14:06
     */
    double getGaoshuMin();

    /**
     * @description: 获取英语最低分
     * @param:
     * @return: double
     * @date: 2023/6/9 0009 14:06
     */
    double getEnglishMin();

    /**
     * @description: 学生列表按成绩降序排列
     * @param:
     * @return: java.util.List<com.student.domain.Student>
     * @date: 2023/6/9 0009 14:07
     */
    List<Student> descByTotal();

    /**
     * @description: 删除学生通过id
     * @param: id
     * @return: boolean
     * @date: 2023/6/9 0009 14:07
     */
    boolean deleteStudentById(String id);

    /**
     * @description: 更新学生
     * @param: student
     * @return: boolean
     * @date: 2023/6/9 0009 14:07
     */
    boolean updateStudent(Student student);

    /**
     * @description: 添加学生
     * @param: student
     * @return: boolean
     * @date: 2023/6/9 0009 14:07
     */
    boolean addStudent(Student student);
}
