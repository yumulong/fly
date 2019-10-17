package org.neuedu.service;

import org.neuedu.bean.User;
import org.neuedu.dao.UserDao;
import org.neuedu.dao.UserDaoImpl;
import org.neuedu.utils.MD5Utils;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    //   用户注册
    @Override
    public int saveRegUser(String email, String nickname, String password) {
             int count=userDao.serchEmail(email);

        if (count == 0) {
            // MD5密码加密
            password = MD5Utils.MD5Encode(password,"utf8");
            int i = userDao.saveUser(email,nickname,password);
            return i;
        }else {
            return 0;
        }

        }






//    用户登录
    @Override
    public User selectLoginUser(String email, String password) {

        password = MD5Utils.MD5Encode(password,"utf8");


        return userDao.loginUser(email,password);
    }
}
