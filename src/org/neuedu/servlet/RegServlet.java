package org.neuedu.servlet;

import org.neuedu.service.UserService;
import org.neuedu.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegServlet",urlPatterns = "/user/reg")
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email=request.getParameter("email");
        String nickname=request.getParameter("username");
        String password=request.getParameter("pass");
        UserService service=new UserServiceImpl() ;

        int i=service.saveRegUser(email,nickname,password);
        if (i==0){
            request.setAttribute("msg","注册邮箱重复");
        }else {



            request.setAttribute("msg","用户注册成功");


        }

        request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
