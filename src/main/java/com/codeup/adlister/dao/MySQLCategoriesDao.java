package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao implements Categories {

    public static void main(String[] args) {
        System.out.println(DaoFactory.getAdsDao().all());
    }

    private Connection connection;

    public MySQLCategoriesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Category> all() {

        List<Category> catList = new ArrayList<>();
        try {
            System.out.println("try started");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categories");
            while(rs.next()){
                Category cat = new Category();
                cat.setId(rs.getLong("id"));
                cat.setTitle(rs.getString("title"));
                cat.setImgUrl(rs.getString("imgUrl"));
                catList.add(cat);
            }
            return catList;
        } catch (SQLException e) {
            System.out.println("exception thrown");
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    private List<Category> createCategoriesFromResults(ResultSet rs) throws SQLException {
        List<Category> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractCategory(rs));
        }
        return ads;
    }

    private Category extractCategory(ResultSet rs) throws SQLException {
            return new Category(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("imgUrl")

            );
        }
    }
