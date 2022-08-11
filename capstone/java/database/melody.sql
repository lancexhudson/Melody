BEGIN TRANSACTION;
DROP TABLE IF EXISTS band, genre, band_genre, band_user;
DROP SEQUENCE IF EXISTS seq_band_id, seq_genre_id;
CREATE SEQUENCE seq_band_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;
CREATE TABLE band (
band_id int NOT NULL DEFAULT nextval('seq_band_id'),
band_name varchar(50) NOT NULL,
description varchar(140) NOT NULL,
genre varchar(20) NOT NULL,
image_link varchar(1000),
CONSTRAINT PK_band_id PRIMARY KEY (band_id)
);
CREATE SEQUENCE seq_genre_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;
CREATE TABLE genre (
genre_id int NOT NULL DEFAULT nextval('seq_genre_id'),
genre_name varchar(25) NOT NULL,
CONSTRAINT PK_genre_id PRIMARY KEY (genre_id)
);
CREATE TABLE band_genre (
band_id int NOT NULL,
genre_id int NOT NULL,
CONSTRAINT PK_band_genre_band_id PRIMARY KEY (band_id),
CONSTRAINT FK_band_id FOREIGN KEY (band_id) REFERENCES band(band_id),
CONSTRAINT FK_genre_id FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);
CREATE TABLE band_user (
band_id int NOT NULL,
user_id int NOT NULL,
CONSTRAINT PK_band_user_band_id PRIMARY KEY (band_id),
CONSTRAINT FK_band_id FOREIGN KEY (band_id) REFERENCES band(band_id),
CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);


COMMIT;