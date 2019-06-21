package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad, String imgURL);
    List<Ad> allFromUser(Long userID);
    void delete(long id);
    void updateAd(Ad ad, long id, String imgUrl);
    List<Ad> one(long id);
    void deleteFromUser(long user_id);
    Long insertCat_Ads(long ad_id, List<String> cat_ids);

    List<Ad> containsAd(String searchQuery);
    List<Ad> adsCats(String title);
}
