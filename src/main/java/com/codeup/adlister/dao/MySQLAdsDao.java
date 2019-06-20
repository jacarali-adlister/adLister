package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;
import java.sql.Date;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    @Override
    public void updateAd(Ad ad, long id, String imgUrl) {
        try {
        String query = "UPDATE adlister_db.ads SET title=?, description = ?, imageUrl = ? where id=" + id;
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, ad.getTitle());
        stmt.setString(2, ad.getDescription());
        stmt.setString(3, imgUrl);
        stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public MySQLAdsDao(Config config) {
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
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public List<Ad> one(long id) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads where id=" + id);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad, String imgURL) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description, create_date, imageUrl) VALUES (?, ?, ?, CURDATE(), ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.setString(4, imgURL);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    @Override
    public Long insertCat_Ads(long ad_id, List<String> cat_ids) {


        try {
        for (String id:cat_ids) {
                long cat_id = Long.parseLong(id);

                String insertQuery = "INSERT INTO ads_category(ad_id, category_id) VALUES (?, ?)";
                PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                stmt.setLong(1, ad_id);
                stmt.setLong(2, cat_id);
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();

        }
            } catch (SQLException e) {
                throw new RuntimeException("Error creating a new ad.", e);
            }
        return 0L;
    }

    @Override
    public List<Ad> containsAd(String searchQuery) {
        return null;
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getDate("create_date"),
            rs.getString("imageUrl")

        );
    }



    @Override
    public List<Ad> allFromUser(Long userID) {
        List <Ad> ads = new ArrayList<>();
        try  {
            Statement statement = connection.createStatement();
            String query = "select id, user_id, title, description, imageUrl from ads where user_id = " + userID;
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                ads.add(
                        new Ad(rs.getLong("id"),
                                rs.getLong("user_id"), rs.getString("title"), rs.getString("description"),
                                rs.getString("imageUrl")
                        )

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ads;
    }

    public void delete(long id) {
        PreparedStatement stmt = null;
        try {
            String query = "DELETE FROM ads WHERE id = ?;";
            stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
    public void deleteFromUser(long user_id) {
        PreparedStatement stmt = null;
        try {
            String query = "DELETE FROM ads WHERE user_id = ?;";
            stmt = connection.prepareStatement(query);
            stmt.setLong(1, user_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
