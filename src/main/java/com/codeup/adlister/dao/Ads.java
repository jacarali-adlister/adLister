package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad, String imgURL);
//    get a list of ads from a particular user
    List<Ad> allFromUser(Long userID);
//    delete an ad
    void delete(long id);
//    change an ad
    void updateAd(Ad ad, long id, String imgUrl);
//    get one ad
    List<Ad> one(long id);
//    delete all ads made by a user
    void deleteFromUser(long user_id);
//    insert ads and categories to the join table
    Long insertCat_Ads(long ad_id, List<String> cat_ids);
//    search for an ad by title, description, or user
    List<Ad> containsAd(String searchQuery);
//    show all ads in a category
    List<Ad> adsCats(String title);
//     show one ad
    Ad thisAd(Long id);

}
