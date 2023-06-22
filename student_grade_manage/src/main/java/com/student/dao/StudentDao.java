package com.student.dao;






import com.student.entity.Student;
import com.student.mapper.StudentMapper;
import com.student.util.DbUtil;
import com.student.util.SqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/***
 * 学生信息dao层
 *
 */

public class StudentDao {
    //创建连接对象
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;



    //添加
    public int  save(Student student){

        String sql = "insert into tb_student (id,name,sex,age,javascore,gaoshuscore,englishscore)  values (? ,? ,? ,? ,? ,? ,? )" ;
        try {
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,student.getId());
            pstmt.setObject(2,student.getName());
            pstmt.setObject(3,student.getSex());
            pstmt.setObject(4,student.getAge());
            pstmt.setObject(5,student.getJavascore());
            pstmt.setObject(6,student.getGaoshuscore());
            pstmt.setObject(7,student.getEnglishscore());

            int i =  pstmt.executeUpdate();
            return i;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,pstmt);
        }
        return 0;
    }

    //删除
    public int delete(String id){
        String sql = "delete from tb_student  where id = ?" ;
        try {
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            int i = pstmt.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(conn,pstmt);
        }
        return 0;
    }

    //修改
    public int update(Student s) {
        String sql = "update tb_student set id = ?,name = ?,sex = ?,age = ?,javascore = ?,gaoshuscore = ? ,englishscore = ? where id = ?";
        try {
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,s.getId());
            pstmt.setObject(2,s.getName());
            pstmt.setObject(3,s.getSex());
            pstmt.setObject(4,s.getAge());
            pstmt.setObject(5,s.getJavascore());
            pstmt.setObject(6,s.getGaoshuscore());
            pstmt.setObject(7,s.getEnglishscore());

            pstmt.setString(8,s.getId());
            int i = pstmt.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(conn,pstmt);
        }
        return 0;
    }


    public ArrayList<Student> queryAll(){
       /* SqlSessionFactory sqlSessionFactory = SqlSessionFactoryBean.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectAllStudent();
        sqlSession.close();
        return (ArrayList<Student>) students;*/
        String sql = "select * from tb_student" ;
        ArrayList<Student> list = new ArrayList<>();
        try {
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);
           // Class clazz = Student.class;
            rs = pstmt.executeQuery();
            // 获取查询的值
            while (rs.next()) {
               Student s=new Student();
                s.setId(rs.getString("id"));
                s.setName(rs.getString("name"));
                s.setSex(rs.getString("sex"));
                s.setAge(rs.getString("age"));

                s.setJavascore(rs.getDouble("javascore"));
                s.setGaoshuscore(rs.getDouble("gaoshuscore"));
                s.setEnglishscore(rs.getDouble("englishscore"));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(conn,pstmt);
        }
        return list;
    }


    //查询
    public Student queryById(String id){
        //String sql = "select * from tb_student where id like ?" ;
        //String sql = "select * from tb_student where id like '%" + sid + "%'";

        String sql="select  * from  tb_student where id =?";
        try {
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);//预编译sql语句
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();

                // 获取查询的值
                while (rs.next()) {
                    Student s = new Student();
                    s.setId(rs.getString("id"));
                    s.setName(rs.getString("name"));
                    s.setSex(rs.getString("sex"));
                    s.setAge(rs.getString("age"));
                    s.setJavascore(rs.getDouble("javascore"));
                    s.setGaoshuscore(rs.getDouble("gaoshuscore"));
                    s.setEnglishscore(rs.getDouble("englishscore"));

                    return s;
                }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(conn,pstmt);
        }
        return null;
    }


    //查询
    public ArrayList<Student> queryname(String uname){
        ArrayList<Student>list=new ArrayList<>();

        String sql = "select * from tb_student where name =?";


        try {
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);//预编译sql语句
            pstmt.setString(1,uname);
            rs = pstmt.executeQuery();
            // 获取查询的值
            while (rs.next()) {

                Student s=new Student();
                s.setId(rs.getString("id"));
                s.setName(rs.getString("name"));
                s.setSex(rs.getString("sex"));
                s.setAge(rs.getString("age"));
                s.setJavascore(rs.getDouble("javascore"));
                s.setGaoshuscore(rs.getDouble("gaoshuscore"));
                s.setEnglishscore(rs.getDouble("englishscore"));
                list.add(s);
                return list;


            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(conn,pstmt);
        }
        return null;
    }

}

