USE adlister_db;

DROP TABLE IF EXISTS ads_category;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
                     id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                     username VARCHAR(240) UNIQUE NOT NULL,
                     email VARCHAR(240) NOT NULL,
                     password VARCHAR(255) NOT NULL,
                     PRIMARY KEY (id)
);

CREATE TABLE ads (
                   id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                   user_id INT UNSIGNED NOT NULL,
                   title VARCHAR(240) NOT NULL,
                   description TEXT NOT NULL,
                   create_date date NOT NULL,
                   imageUrl TEXT NOT NULL,
                   PRIMARY KEY (id),
                   FOREIGN KEY (user_id) REFERENCES users(id)
                     ON DELETE CASCADE
);

CREATE TABLE categories(
                         id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                         title VARCHAR(240) NOT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE ads_category(
                           ad_id INT UNSIGNED NOT NULL,
                           category_id INT UNSIGNED NOT NULL,
                           FOREIGN KEY (ad_id) REFERENCES ads(id)
                             on delete cascade,
                           FOREIGN KEY (category_id) REFERENCES categories(id)
                             on delete cascade
);