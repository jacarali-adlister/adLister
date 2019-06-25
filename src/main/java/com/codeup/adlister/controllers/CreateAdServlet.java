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

            List<String> cat_ids = catified(cat_id);


            String imgURL = request.getParameter("url");

                long id = DaoFactory.getAdsDao().insert(ad, imgURL);
                response.sendRedirect("/ads");
                DaoFactory.getAdsDao().insertCat_Ads(id, cat_ids);
    }

//    sets categories to ad, or gives it the default of general
    private static List<String> catified(String[] arr){
        if (arr != null) {
            List<String> idsarr = Arrays.asList(arr);
            return idsarr;
        } else {
            List<String> idarr= new ArrayList<>();
            idarr.add("19");
            return idarr;
        }
    }
}
