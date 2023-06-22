package com.student.service.Impl;

import com.student.domain.Student;
import com.student.mapper.StudentMapper;
import com.student.service.StudentService;
import com.student.util.SqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author aloneAtri
 * @version 1.0
 * @description 描述信息
 * @date 2023/6/1 0001 18:34
 */
public class StudentServiceImpl implements StudentService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryBean.getSqlSessionFactory();

    public Student getStudentByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student studentByName = mapper.getStudentByName(name);
        sqlSession.close();
        return studentByName;
    }

    public Student getStudentById(String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student studentById = mapper.getStudentById(id);
        sqlSession.close();
        return studentById;
    }

    public List<Student> getAllStudent() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectAllStudent();
        sqlSession.close();
        return students;
    }

    public double getJavaScoreAvg() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        double javaScoreAvg = mapper.getJavaScoreAvg();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        sqlSession.close();
        return Double.parseDouble(decimalFormat.format(javaScoreAvg));
    }

    public double getGaoshuScoreAvg() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        double gaoshuScoreAvg = mapper.getGaoshuScoreAvg();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        sqlSession.close();
        return Double.parseDouble(decimalFormat.format(gaoshuScoreAvg));
    }

    public double getEnglishScoreAvg() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        double englishScoreAvg = mapper.getEnglishScoreAvg();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        sqlSession.close();
        return Double.parseDouble(decimalFormat.format(englishScoreAvg));
    }

    public double getJavaMax() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        double javaMax = mapper.getJavaMax();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        sqlSession.close();
        return Double.parseDouble(decimalFormat.format(javaMax));
    }

    public double getGaoshuMax() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        double gaoshuMax = mapper.getGaoshuMax();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        sqlSession.close();
        return Double.parseDouble(decimalFormat.format(gaoshuMax));
    }

    public double getEnglishMax() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        double englishMax = mapper.getEnglishMax();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        sqlSession.close();
        return Double.parseDouble(decimalFormat.format(englishMax));
    }

    public double getJavaMin() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        double javaMin = mapper.getJavaMin();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        sqlSession.close();
        return Double.parseDouble(decimalFormat.format(javaMin));
    }

    public double getGaoshuMin() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        double gaoshuMin = mapper.getGaoshuMin();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        sqlSession.close();
        return Double.parseDouble(decimalFormat.format(gaoshuMin));
    }

    public double getEnglishMin() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        double englishMin = mapper.getEnglishMin();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        sqlSession.close();
        return Double.parseDouble(decimalFormat.format(englishMin));
    }

    public List<Student> descByTotal() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.descByTotal();
        sqlSession.close();
        return students;
    }

    public boolean deleteStudentById(String id) {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.deleteStudentById(id);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    public boolean updateStudent(Student student) {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.updateStudent(student);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    public boolean addStudent(Student student) {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.addStudent(student);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
}
