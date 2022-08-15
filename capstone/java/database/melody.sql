BEGIN TRANSACTION;
DROP TABLE IF EXISTS band, genre, band_genre, band_user, user_favorite_bands, messages;
DROP SEQUENCE IF EXISTS seq_band_id, seq_genre_id, seq_message_id;
CREATE SEQUENCE seq_band_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;
CREATE TABLE band (
band_id int NOT NULL DEFAULT nextval('seq_band_id'),
band_name varchar(50) NOT NULL,
description varchar(1000) NOT NULL,
image_link varchar(2500),
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
CONSTRAINT PK_band_genre_band_id PRIMARY KEY (band_id, genre_id),
CONSTRAINT FK_band_id FOREIGN KEY (band_id) REFERENCES band(band_id),
CONSTRAINT FK_genre_id FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);
CREATE TABLE user_favorite_bands (
user_id int NOT NULL,
band_id int NOT NULL,
CONSTRAINT PK_band_fan PRIMARY KEY (user_id, band_id),
CONSTRAINT FK_band_id FOREIGN KEY (band_id) REFERENCES band(band_id),
CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE band_user (
band_id int NOT NULL,
user_id int NOT NULL,
CONSTRAINT PK_band_user_band_id PRIMARY KEY (band_id),
CONSTRAINT FK_band_id FOREIGN KEY (band_id) REFERENCES band(band_id),
CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE SEQUENCE seq_message_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE;
  
CREATE TABLE messages (
user_id int NOT NULL,
band_id int NOT NULL,
message varchar(1000),
date varchar(11),
message_id int NOT NULL DEFAULT nextval('seq_message_id'),
CONSTRAINT PK_message_id PRIMARY KEY (message_id),
CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
CONSTRAINT FK_band_id FOREIGN KEY (band_id) REFERENCES band(band_id)	
);

INSERT INTO genre (genre_name) 
VALUES ('Pop'), ('Rock'), ('HipHop'), ('Classical'), ('R&B'),('Electronic'), ('Country'), 
		('Blues'), ('Jazz'), ('Metal'), ('Reggae'), ('Folk'), ('Latin'), ('A Capella'), ('Funk'), 
		('Worship'), ('Instrumental'), ('Punk'), ('Singer/Songwriter'), ('World');


COMMIT;