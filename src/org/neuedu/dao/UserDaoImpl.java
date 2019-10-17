package org.neuedu.dao;

import org.neuedu.bean.User;
import org.neuedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

//    用户名查询


    @Override
    public int serchEmail(String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try{
            conn = DBUtils.getInstance().getConnection();
            String sql = "select count(*) counts from user where email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,email);
            rs = ps.executeQuery();
            if(rs.next()){
                count = rs.getInt("counts");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }

    //      用户注册
    @Override
    public int saveUser(String email, String nickname, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try{
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into user(email,password,nickname) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,password);
            ps.setString(3,nickname);
            count = ps.executeUpdate();
            conn.commit();
        }catch (Exception e){
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }



//    用户登录
    @Override
    public User loginUser(String email, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try{
            conn = DBUtils.getInstance().getConnection();
            String sql = "select * from user where email = ? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();

                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return user;
    }
}
