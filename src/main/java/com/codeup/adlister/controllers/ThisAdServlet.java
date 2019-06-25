package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="ThisAdServlet",urlPatterns = "/thisAd")
public class ThisAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("id");
        Long id = Long.parseLong(idString);
        Ad ad = DaoFactory.getAdsDao().thisAd(id);
        User user = DaoFactory.getUsersDao().findByUserId(ad.getUserId());
        request.setAttribute("user", user);
        request.setAttribute("ad", ad);
        request.getRequestDispatcher("/WEB-INF/ads/thisAd.jsp").forward(request, response);
    }

}
