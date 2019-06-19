package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "updateprofile",urlPatterns = "/updateprofile")
public class UpdateProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("user",user);
        req.getRequestDispatcher("/WEB-INF/updateprofile.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NullPointerException {

        User user =(User) req.getSession().getAttribute("user");

        String passWord = Password.hash(req.getParameter("password"));
        String username = req.getParameter("username");
        User updateUser = new User(
                username,
                req.getParameter("email"),
                passWord

        );
        System.out.println(passWord);

        DaoFactory.getUsersDao().updateprofile(updateUser, user.getId());
        resp.sendRedirect("logout");
    }
}
