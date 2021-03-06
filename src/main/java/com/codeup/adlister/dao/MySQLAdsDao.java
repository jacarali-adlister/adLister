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
    private String url = "https://via.placeholder.com/150";
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
        if(imgURL == null || imgURL == ""){
            imgURL = url;
        }
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

    public List<Ad> containsAd(String searchQuery) {
        try {
            String selectQuery = "SELECT * FROM ads JOIN users ON users.id = ads.user_id WHERE ads.title LIKE ? OR ads.description LIKE ? OR users.username LIKE ?";
            PreparedStatement stmt = connection.prepareStatement(selectQuery);
            stmt.setString(1,"%" + searchQuery + "%");
            stmt.setString(2,"%" + searchQuery + "%");
            stmt.setString(3,"%" + searchQuery + "%");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("It didn't work", e);
        }

    }

//    makes Ad objects from database rows
    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getDate("create_date"),
            rs.getString("imageUrl"),
            cat_ads(rs.getLong("id")),
            adUsername(rs.getLong("user_id"))
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
                                rs.getString("imageUrl"),
                                cat_ads(rs.getLong("id"))
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

//    builds a list of Ad objects
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
//  get array of category ids for an ad
    private String[] cat_ads(Long ad_id){
        PreparedStatement stmt = null;
        try {
        String categories = "";
        String query ="SELECT title from categories join ads_category on categories.id = ads_category.category_id where ads_category.ad_id = " + ad_id;
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                categories += rs.getString("title") + ",";
            }
            String[] catsArr = categories.split(",");
            return catsArr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
         String[] arr = {"general"};
        return arr;
    }

//    get the username for an ad
    private String adUsername(Long userId){
        PreparedStatement stmt = null;
        try {
            String adsUsername = "";
            String query = "select username from users join ads on users.id = ads.user_id where ads.user_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            adsUsername = rs.getString("username");
            return adsUsername;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }

    public List<Ad> adsCats(String title) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("select ads.id, ads.user_id, ads.title, ads.description, ads.create_date, ads.imageUrl from ads join ads_category ac on ads.id = ac.ad_id join categories c on ac.category_id = c.id where c.title = ?;");
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Ad thisAd(Long id) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            List<Ad> ads = createAdsFromResults(rs);
            return ads.get(0);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving this ad.", e);
        }

    }

}
