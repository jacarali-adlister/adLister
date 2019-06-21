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

@WebServlet(name="UpdateAdServlet", urlPatterns = "/update-ad")
public class UpdateAdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }

        long id = (long)request.getSession().getAttribute("update-id");

        User user = (User) request.getSession().getAttribute("user");

        request.setAttribute("ads", DaoFactory.getAdsDao().one(id));

        request.getRequestDispatcher("/WEB-INF/ads/update-ad.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        long id = (long)request.getSession().getAttribute("update-id");
        if (request.getParameter("delete") != null){
            DaoFactory.getAdsDao().delete(id);
        } else {

            Ad ad = new Ad(
                    user.getId(), // for now we'll hardcode the user id
                    request.getParameter("title"),
                    request.getParameter("description")

            );

            String imgURL = request.getParameter("url");
            if (request.getParameter("url") == null) {
                imgURL = "https://via.placeholder.com/150";
            }

            DaoFactory.getAdsDao().updateAd(ad, id, imgURL);
        }
        response.sendRedirect("/profile");
    }
}
