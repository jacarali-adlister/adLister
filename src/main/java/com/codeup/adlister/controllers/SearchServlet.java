package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.codeup.adlister.models.UserAd;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "controllers.SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String searchQuery = request.getParameter("search");
        List<Ad> foundAds = DaoFactory.getAdsDao().containsAd(searchQuery);
//        List<UserAd> userAds = new ArrayList<>();
//        for (Ad ad : foundAds) {
//            UserAd userAd = new UserAd();
//            User user = DaoFactory.getUsersDao().findByUserId(ad.getUserId());
//            userAd.setAd(ad);
//            userAd.setUser(user);
//            userAds.add(userAd);
//        }
        request.setAttribute("userAds", foundAds);
        request.getRequestDispatcher("/WEB-INF/search.jsp").forward(request,response);
    }
}
