package org.neuedu.service;

import org.neuedu.bean.User;

public interface UserService {




    //    用户注册（email,nickname,password）
    int saveRegUser(String email,String nickname,String password);


    //    用户登录（email,password）
    User selectLoginUser(String email, String passworc);
}
