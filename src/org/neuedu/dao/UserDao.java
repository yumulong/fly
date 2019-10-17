package org.neuedu.dao;

import org.neuedu.bean.User;

public interface UserDao {
//    用户名查询是否重复
    int serchEmail(String email);


//    用户注册（email,nickname,password）
    int saveUser(String email,String nickname,String password);



//    用户登录（email,password）
    User loginUser(String email,String passworc);
}
