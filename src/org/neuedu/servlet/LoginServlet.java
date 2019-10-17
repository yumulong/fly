package org.neuedu.servlet;

import org.neuedu.bean.User;
import org.neuedu.service.UserService;
import org.neuedu.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/user/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        String pass=request.getParameter("pass");
        UserService service=new UserServiceImpl();
        User user=new User();
        user=service.selectLoginUser(email,pass);
        if (user==null){
            request.setAttribute("msg","账号密码错误");
            request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request,response);
        }else {

            request.getRequestDispatcher("/WEB-INF/jsp/user/home.jsp").forward(request,response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
