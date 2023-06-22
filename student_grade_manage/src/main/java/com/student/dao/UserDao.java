package com.student.dao;

import com.student.entity.Student;
import com.student.entity.User;
import com.student.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDao {


    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public ArrayList<User> queryAll(){
        String sql = "select * from tb_user " ;
        ArrayList<User> list = new ArrayList<>();
        try {
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);
            Class clazz = Student.class;
            rs = pstmt.executeQuery();
            // 获取查询的值
            while (rs.next()) {
               User s=new User();
                s.setId(rs.getInt("id"));
                s.setUsername(rs.getString("username"));
                s.setPassword(rs.getString("password"));
                s.setSex(rs.getString("sex"));
                s.setAge(rs.getInt("age"));

                s.setAddress(rs.getString("address"));
                s.setTel(rs.getString("tel"));
                s.setPost(rs.getString("post"));
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

    //删除
    public int delete(int id){
        String sql = "delete from tb_user  where id = ?" ;
        try {
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            int i = pstmt.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(conn,pstmt);
        }
        return 0;
    }



    public User queryByName(String name){
        String sql = "select * from tb_user where username = ?" ;
        try {
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);//预编译sql语句
            pstmt.setString(1,name);
            rs = pstmt.executeQuery();
            // 获取查询的值
            while (rs.next()) {
                User users = new User();
                users.setId(rs.getInt("id"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));

                return users;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(conn,pstmt,rs);
        }
        return null;
    }

    public int save(User users){

        String sql = "insert into tb_user (id,username,password,sex,age,address,tel,post)  values (? ,? ,? ,?,? ,? ,? ,?)" ;
        try {
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,null);
            pstmt.setObject(2,users.getUsername());
            pstmt.setObject(3,users.getPassword());
            pstmt.setObject(4,users.getSex());
            pstmt.setObject(5,users.getAge());
            pstmt.setObject(6,users.getAddress());
            pstmt.setObject(7,users.getTel());
            pstmt.setObject(8,users.getPost());

            int i =  pstmt.executeUpdate();
            return i;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,pstmt);
        }
        return 0;
    }

    public int update(User user) {
        String sql = "update tb_user set username = ?,password = ? where username = ?";
        try {
            conn = DbUtil.getCon();
            pstmt = conn.prepareStatement(sql);

            pstmt.setObject(1,user.getUsername());
            pstmt.setObject(2,user.getPassword());

            pstmt.setString(3,user.getUsername());
            int i = pstmt.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbUtil.close(conn,pstmt);
        }
        return 0;
    }

}
