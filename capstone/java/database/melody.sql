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
date varchar(100),
message_id int NOT NULL DEFAULT nextval('seq_message_id'),
CONSTRAINT PK_message_id PRIMARY KEY (message_id),
CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
CONSTRAINT FK_band_id FOREIGN KEY (band_id) REFERENCES band(band_id)	
);

INSERT INTO genre (genre_name) 
VALUES ('Pop'), ('Rock'), ('HipHop'), ('Classical'), ('R&B'),('Electronic'), ('Country'), 
		('Blues'), ('Jazz'), ('Metal'), ('Reggae'), ('Folk'), ('Latin'), ('A Capella'), ('Funk'), 
		('Worship'), ('Instrumental'), ('Punk'), ('Singer/Songwriter'), ('World');

INSERT INTO band (band_name, description, image_link)
VALUES ('The Beatles',
'This four piece band from Liverpool, England took the world by storm in the 1960s and is credited for being one the leading bands in the British Invasion of the American music scene.',
'https://roadiecrew.com/wp-content/uploads/BEATLES-1970-Foto-Divulgacao-Apple-Corps.-Ltd.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1001, 2001);
INSERT INTO band_genre (band_id, genre_id)
VALUES (1001, 2002);

INSERT INTO band (band_name, description, image_link)
VALUES ('Led Zeppelin',
'Led Zeppelin were an English rock band formed in London in 1968.The group consisted of vocalist Robert Plant, guitarist Jimmy Page, bassist/keyboardist John Paul Jones, and drummer John Bonham. With a heavy, guitar-driven sound, they are cited as one of the progenitors of hard rock and heavy metal, although their style drew from a variety of influences, including blues and folk music.',
'https://www.rollingstone.com/wp-content/uploads/2019/01/led-zeppelin-1969.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1002, 2002);

INSERT INTO band (band_name, description, image_link)
VALUES ('Queen',
'Queen are a British rock band formed in London in 1970. The band comprised Freddie Mercury (lead vocals, piano), Brian May (guitar, vocals), Roger Taylor (drums, vocals) and John Deacon (bass). Their earliest works were influenced by progressive rock, hard rock and heavy metal, but the band gradually ventured into more conventional and radio-friendly works by incorporating further styles, such as arena rock and pop rock.',
'https://www.wnrn.org/wp-content/uploads/2019/10/queen.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1003, 2002);

INSERT INTO band (band_name, description, image_link)
VALUES ('Green Day',
'Green Day is an American rock band formed in the East Bay of California in 1987 by lead vocalist and guitarist Billie Joe Armstrong, together with bassist and backing vocalist Mike Dirnt. For most of the career of the band, they have been a power trio with drummer Tré Cool, who replaced John Kiffmeyer in 1990 before the recording of their second studio album, Kerplunk (1991).',
'https://media.altpress.com/uploads/2019/09/green-day-ap-cover.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1004, 2002);
INSERT INTO band_genre (band_id, genre_id)
VALUES (1004, 2018);

INSERT INTO band (band_name, description, image_link)
VALUES ('My Chemical Romance',
'My Chemical Romance (commonly abbreviated to MCR or My Chem) is an American rock band from Newark, New Jersey. Their current lineup consists of lead vocalist Gerard Way, lead guitarist Ray Toro, rhythm guitarist Frank Iero, and bassist Mikey Way. They are considered one of the most influential rock groups of the 2000s and a major act in the pop-punk and emo genres, despite the band rejecting the latter label.',
'https://i.ytimg.com/vi/RRKJiM9Njr8/maxresdefault.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1005, 2002);

INSERT INTO band (band_name, description, image_link)
VALUES ('Weezer',
'Weezer is an American rock band formed in Los Angeles, California, in 1992. Since 2001, the band has consisted of Rivers Cuomo (lead vocals, lead guitar, keyboards), Patrick Wilson (drums), Scott Shriner (bass guitar, keyboards, backing vocals), and Brian Bell (rhythm guitar, keyboards, backing vocals).',
'https://www.hollywoodreporter.com/wp-content/uploads/2018/12/weezer.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1006, 2001);
INSERT INTO band_genre (band_id, genre_id)
VALUES (1006, 2002);

INSERT INTO band (band_name, description, image_link)
VALUES ('Panic! At The Disco',
'Panic! at the Disco is the solo project of American musician Brendon Urie. It was originally a pop rock band from Las Vegas, Nevada, formed in 2004 by childhood friends Urie, Ryan Ross, Spencer Smith, and Brent Wilson. They recorded their first demos while they were in high school.',
'https://www.rollingstone.com/wp-content/uploads/2022/05/panic-at-the-disco-urie-cryptic-site.jpg?resize=1800,1200&w=1800');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1007, 2001);
INSERT INTO band_genre (band_id, genre_id)
VALUES (1007, 2002);

INSERT INTO band (band_name, description, image_link)
VALUES ('Metallica',
'Metallica is an American heavy metal band. The band was formed in 1981 in Los Angeles by vocalist/guitarist James Hetfield and drummer Lars Ulrich and has been based in San Francisco for most of its career. Their fast tempos, instrumentals and aggressive musicianship made them one of the founding "big four" bands of thrash metal, alongside Megadeth, Anthrax, and Slayer. Their current lineup comprises founding members and primary songwriters Hetfield and Ulrich, longtime lead guitarist Kirk Hammett, and bassist Robert Trujillo. Guitarist Dave Mustaine (who formed Megadeth after being fired from the band) and bassists Ron McGovney, Cliff Burton, and Jason Newsted are former members of the band.',
'http://www.udiscovermusic.com/wp-content/uploads/2020/11/Metallica-GettyImages-531257207-1000x600.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1008, 2010);

INSERT INTO band (band_name, description, image_link)
VALUES ('Dave Matthews Band',
'Dave Matthews Band (also known by the initials DMB) is an American rock band formed in Charlottesville, Virginia, in 1991. Their founding members were singer-songwriter and guitarist Dave Matthews, bassist Stefan Lessard, drummer and backing vocalist Carter Beauford, violinist and backing vocalist Boyd Tinsley, and saxophonist LeRoi Moore. As of 2022, Matthews, Lessard, and Beauford are the only remaining founding members still performing with the band.',
'https://d.newsweek.com/en/full/1064025/dave-matthews-band.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1009, 2012);
INSERT INTO band_genre (band_id, genre_id)
VALUES (1009, 2019);


INSERT INTO band (band_name, description, image_link)
VALUES ('Foo Fighters',
'Foo Fighters are an American rock band formed in Seattle in 1994. Foo Fighters was initially formed as a one man project by former Nirvana drummer Dave Grohl. Following the success of their eponymous debut album Foo Fighters, Grohl (lead vocals, guitar) recruited a band consisting of Nate Mendel (bass guitar), William Goldsmith (drums), and Pat Smear (guitar). After a succession of lineup changes, including the departures of Goldsmith and Smear, the band formed its core lineup in 1999 consisting of Grohl, Mendel, Chris Shiflett (guitar), and Taylor Hawkins (drums). In 2005 Smear returned to the band and in 2017 Rami Jaffee joined the band performing the keyboards and piano.',
'https://iscale.iheart.com/catalog/artist/72280');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1010, 2001);

INSERT INTO band (band_name, description, image_link)
VALUES ('Daft Punk', 'Daft Punk were a French electronic music duo formed in 1993 in Paris by Thomas Bangalter and Guy-Manuel de Homem-Christo. Widely regarded as one of the most influential acts in dance music history, they achieved popularity in the late 1990s as part of the French house movement. They garnered critical acclaim and commercial success in the years following, combining elements of house music with funk, disco, indie rock and pop.', 'https://upload.wikimedia.org/wikipedia/commons/e/ed/ThomasBangalter028.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1011, 2006);

INSERT INTO band (band_name, description, image_link)
VALUES ('Darius Rucker', 'Darius Carlos Rucker (born May 13, 1966) is an American singer and songwriter. He first gained fame as the lead vocalist and rhythm guitarist of rock band Hootie & the Blowfish, which he founded in 1986 at the University of South Carolina along with Mark Bryan, Jim "Soni" Sonefeld, and Dean Felber. The band released five studio albums with Rucker as a member and charted six top 40 hits on the Billboard Hot 100. Rucker co-wrote most of the songs with the other members of the band.', 'https://res.cloudinary.com/usga/image/upload/c_fill,g_face,q_70,w_800/v1/usga/images/clubhouse/2016/2016-MC-images/DariusRucker.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1012, 2007);




COMMIT;