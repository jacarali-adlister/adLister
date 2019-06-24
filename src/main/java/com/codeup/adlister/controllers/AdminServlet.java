package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        if (!user.getUsername().contains("admin")){
            response.sendRedirect("/profile");
        }
        request.setAttribute("users", DaoFactory.getUsersDao().allUsers());
        request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long user_id = Long.parseLong(request.getParameter("id"));
        DaoFactory.getAdsDao().deleteFromUser(user_id);
       DaoFactory.getUsersDao().deleteUser(user_id);

       response.sendRedirect("/admin");
    }
}
