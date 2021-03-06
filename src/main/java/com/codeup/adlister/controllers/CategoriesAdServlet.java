package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoriesAdServlet", urlPatterns = "/adcat")
public class CategoriesAdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jasonCast = (String) request.getSession().getAttribute("category");
        request.setAttribute("ads", DaoFactory.getAdsDao().adsCats(jasonCast));
        request.getRequestDispatcher("/WEB-INF/ads/adcat.jsp").forward(request, response);
    }
}
