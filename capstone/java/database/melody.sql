BEGIN TRANSACTION;
DROP TABLE IF EXISTS band, genre, band_genre, band_user, user_favorite_bands, messages, events;
DROP SEQUENCE IF EXISTS seq_band_id, seq_genre_id, seq_message_id, seq_event_id;
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

CREATE SEQUENCE seq_event_id
  INCREMENT BY 1
  START WITH 4001
  NO MAXVALUE;

CREATE TABLE events (
event_id int NOT NULL DEFAULT nextval('seq_event_id'),
event_date DATE,  
event_time TIME,
venue varchar(100),
band_id int NOT NULL,
CONSTRAINT PK_event_id PRIMARY KEY (event_id),
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
'https://m.media-amazon.com/images/I/71nYpz++VCL._SL1400_.jpg');
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
VALUES ('Siouxsie and the Banshees', 'Siouxsie and the Banshees were a British rock band, formed in London in 1976 by vocalist Siouxsie Sioux and bass guitarist Steven Severin. They have been widely influential, both over their contemporaries and with later acts. Q magazine included John McKay''s guitar playing on Hong Kong Garden in their list of 100 Greatest Guitar Tracks Ever, while Mojo rated guitarist John McGeoch in their list of 100 Greatest Guitarists of All Time for his work on Spellbound. The Times called the group one of the most audacious and uncompromising musical adventurers of the post-punk era.',
'https://upload.wikimedia.org/wikipedia/en/6/6f/Siouxsie_and_the_banshees_79.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1012, 2002);

INSERT INTO band (band_name, description, image_link)
VALUES ('Willie Nelson', 'Willie Hugh Nelson (born April 29, 1933) is an American country musician. The critical success of the album Shotgun Willie (1973), combined with the critical and commercial success of Red Headed Stranger (1975) and Stardust (1978), made Nelson one of the most recognized artists in country music. He was one of the main figures of outlaw country, a subgenre of country music that developed in the late 1960s as a reaction to the conservative restrictions of the Nashville sound',
		'https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Willie_Nelson%2C_Fort_Wayne%2C_Indiana_%28June_27%2C_2018%29_-_Pamela_Smith_06.jpg/1280px-Willie_Nelson%2C_Fort_Wayne%2C_Indiana_%28June_27%2C_2018%29_-_Pamela_Smith_06.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1013, 2007);

INSERT INTO band (band_name, description, image_link)
VALUES ('Erykah Badu', 'Erica Abi Wright (born February 26, 1971), known professionally as Erykah Badu, is an American singer-songwriter, record producer and actress. Influenced by R&B, 1970s soul, and 1980s hip hop, Badu became associated with the neo soul subgenre in the 1990s and 2000s along with artists such as D''Angelo and Maxwell. She has been called the ''Queen of Neo Soul''. Badu''s career began after she opened a show for D''Angelo in 1994 in Fort Worth; record label executive Kedar Massenburg was highly impressed with her performance and signed her to Kedar Entertainment. Her first album, Baduizm, was released in February 1997. It spawned four singles: ''On & On'', ''Appletree'', ''Next Lifetime'' and ''Otherside of the Game.'' The album was certified triple Platinum by the Recording Industry Association of America (RIAA). Her first live album, Live, was released in November 1997 and was certified double Platinum by the RIAA.',
'https://upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Erykah_badu-02-mika.jpg/1024px-Erykah_badu-02-mika.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1014, 2005);

INSERT INTO band (band_name, description, image_link)
VALUES ('Albert King', 'Albert Nelson (April 25, 1923 – December 21, 1992), known by his stage name Albert King, was an American blues guitarist and singer whose playing influenced many other blues guitarists. He is perhaps best known for the popular and influential album Born Under a Bad Sign (1967) and its title track. He, B.B. King, and Freddie King, all unrelated, were known as the ''Kings of the Blues''. The left-handed King was known for his ''deep, dramatic sound that was widely imitated by both blues and rock guitarists.''',
'https://upload.wikimedia.org/wikipedia/commons/3/30/Albert_King.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1015, 2008);

INSERT INTO band (band_name, description, image_link)
VALUES ('Culture', 'Culture are a Jamaican roots reggae group founded in 1976. Originally they were known as the African Disciples. The one constant member until his death in 2006 was Joseph Hill.',
'https://wildharemusic.com/wp-content/uploads/2014/09/Culture.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1016, 2011);

INSERT INTO band (band_name, description, image_link)
VALUES ('Juanes', 'Juan Esteban Aristizábal Vásquez (born August 9, 1972), known professionally as Juanes, is a Colombian musician who was a member of the rock band Ekhymosis and is now a solo artist. In 2000, his solo debut album Fíjate Bien won three Latin Grammy Awards. According to his record label, Juanes has sold more than 15 million albums worldwide.',
'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Juanes_FPML.JPG/1920px-Juanes_FPML.JPG');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1017, 2013);

INSERT INTO band (band_name, description, image_link)
VALUES ('Rick James', 'James Ambrose Johnson Jr. (February 1, 1948 – August 6, 2004), better known by his stage name Rick James, was an American singer-songwriter, musician and record producer. Born and raised in Buffalo, New York, James began his musical career in his teenage years. He was in various bands before entering the U.S. Navy Reserve to avoid being drafted into the army. In 1964, James deserted to Toronto, Canada, where he formed the rock band the Mynah Birds, who eventually signed a recording deal with Motown Records in 1966. James'' career with the group halted after military authorities discovered his whereabouts and eventually convicted and sentenced James to a one-year prison term related to the desertion charges. After being released, James moved to California, where he started a variety of rock and funk groups in the late 1960s and early 1970s.',
'https://allthatsinteresting.com/wordpress/wp-content/uploads/2021/08/portrait-of-rick-james.jpg');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1018, 2015);

INSERT INTO band (band_name, description, image_link)
VALUES ('Milt Jackson', 'Milton Jackson (January 1, 1923 – October 9, 1999), nicknamed ''Bags'', was an American jazz vibraphonist, usually thought of as a bebop player, although he performed in several jazz idioms. He is especially remembered for his cool swinging solos as a member of the Modern Jazz Quartet and his penchant for collaborating with hard bop and post-bop players.',
'https://rnz-ressh.cloudinary.com/image/upload/s--e3bL4MRO--/c_scale,f_auto,q_auto,w_1050/4N3FP6H_image_crop_90605');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1019, 2009);

INSERT INTO band (band_name, description, image_link)
VALUES ('Dirty Three', 'Dirty Three is an Australian instrumental rock band, consisting of Warren Ellis (violin and bass guitar), Mick Turner (electric and bass guitars) and Jim White (drums), which formed in 1992. Their 1996 album Horse Stories was voted by Rolling Stone as one of the top three albums of the year. Two of their albums have peaked into the top 50 on the ARIA Albums Chart, Ocean Songs (1998) and Toward the Low Sun (2012). During their career they have spent much of their time overseas when not performing together. Turner is based in Melbourne, White lives in New York, and Ellis in Paris. Australian rock music historian Ian McFarlane described them as providing a ''rumbling, dynamic sound incorporated open-ended, improvisational, electric rock ... minus the jazz-rock histrionics''. In October 2010, Ocean Songs was listed in the book 100 Best Australian Albums.',
'https://media.npr.org/assets/img/2012/02/13/dirtythree-a9f5f46f62c84abd1de3ea4c6a1ac7696a61219d-s300-c85.webp');
INSERT INTO band_genre (band_id, genre_id)
VALUES (1020, 2017);



COMMIT;