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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null){

            request.setAttribute("categories", DaoFactory.getCategoriesDao().all());

            request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
            .forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        Ad ad = new Ad(
                user.getId(), // for now we'll hardcode the user id
                request.getParameter("title"),
                request.getParameter("description")

        );

        String[] cat_id = request.getParameterValues("categories");

        if (cat_id != null) {
            List<String> cat_ids = new ArrayList<String>(Arrays.asList(cat_id));

            String imgURL = request.getParameter("url");
            if (request.getParameter("url") == null) {
                imgURL = "https://via.placeholder.com/150";

                long id = DaoFactory.getAdsDao().insert(ad, imgURL);
                DaoFactory.getAdsDao().insertCat_Ads(id, cat_ids);
            } else {

                long id = DaoFactory.getAdsDao().insert(ad, imgURL);
                response.sendRedirect("/ads");
                DaoFactory.getAdsDao().insertCat_Ads(id, cat_ids);
            }

        } else {
            String[] temp = {"19"};
            List<String> cat_ids = new ArrayList<String>(Arrays.asList(temp));

            String imgURL = request.getParameter("url");
            if (request.getParameter("url") == null) {
                imgURL = "https://via.placeholder.com/150";

                long id = DaoFactory.getAdsDao().insert(ad, imgURL);
                DaoFactory.getAdsDao().insertCat_Ads(id, cat_ids);
            } else {

                long id = DaoFactory.getAdsDao().insert(ad, imgURL);
                response.sendRedirect("/ads");
                DaoFactory.getAdsDao().insertCat_Ads(id, cat_ids);
            }

        }

    }
}
