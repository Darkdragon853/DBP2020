-- Testfälle provozieren

-- Build a continent
INSERT INTO continent(name) VALUES ('Europe');

-- Build a country
INSERT INTO country(name, continent_id) VALUES ('Netherlands', '1');

-- Build a city
INSERT INTO city(name, country_id) VALUES ('Amsterdam', '1');


-- Build a person which should not be accepted bc of email
INSERT INTO person(creationDate, firstName, lastName, gender, birthday, email, speaks, browserUsed, locationIP, city_id) 
VALUES ('2020-02-03', 'Adam', 'Smith', 'Male', '1999-02-03', '{lol@gm@x.de, shaast@gmail.com}','{damalsAlsEsNochGutWar}', 'Firefox', '56.43.234.12', 1);
-- Build a person which should not be accepted bc of birthday
INSERT INTO person(creationDate, firstName, lastName, gender, birthday, email, speaks, browserUsed, locationIP, city_id) 
VALUES ('2020-02-03', 'Monika', 'Smith', 'Female', '2021-02-03', '{lod@gmx.de}','{damalsAlsddssdEsNochGutWar}', 'Firefox', '75.4.234.12', 1);


-- Build a person which should not be accepted bc of email
INSERT INTO person(creationDate, firstName, lastName, gender, birthday, email, speaks, browserUsed, locationIP, city_id) 
VALUES ('2020-02-03', 'Irina', 'Meyer', 'Female', '1992-07-03', '{mussaberlos.sofort@oderlieber.spa}','{en ,de}', 'Firefox', '56.43.234.12', 1);
