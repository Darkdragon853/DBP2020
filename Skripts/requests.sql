-- 2.b)
-- (1) In wie vielen verschiedenen afrikanischen Städten gibt es eine Universität?
SELECT COUNT(DISTINCT City.id) FROM (University JOIN City ON University.city_id = City.id JOIN Country ON City.country_id = Country.id JOIN Continent ON Country.continent_id = Continent.id) WHERE Continent.name = 'Africa';

-- Ergbenis: 100

-- (3) Wie viele Kommentare zu Posts gibt es aus jedem Land (Ausgabe aufsteigend sortiert nach Kommentaranzahl)? Die Liste soll auch Länder enthalten, für die keine Post-Kommentare existieren, d.h. die Kommentaranzahl = 0 ist! (Funktion Coalesce)
SELECT Country.name, COALESCE(COUNT(Comment.id), 1) AS NumberOfComments FROM (Comment JOIN Country on Comment.country_id = Country.id) Where Comment.reply_to_post_id IS NOT NULL GROUP BY Country.name ORDER BY 2 ASC;
-- hier fehlen immer noch 4 Einträge...

-- (5) Mit wem ist ‘Hans Johansson’ befreundet?
 
SELECT * FROM pkp_symmetric WHERE person_1_id = (SELECT id FORM person WHERE (firstname = 'Hans') AND (lastName = 'Johansson')); 
-- (7) Welche Nutzer sind Mitglied in allen Foren, in denen auch ‘Mehmet Koksal’ Mitglied ist (Angabe Name)?
 
-- (9) Zu welchen Themen (‘tag classes’) gibt es die meisten Posts? Geben Sie die Namen der Top 10 ‘tag classes’ mit ihrer Häufigkeit aus!
SELECT tc.name FROM (tagclass AS tc JOIN )LIMIT 10;
-- (11) Welche Foren enthalten mehr Posts als die durchschnittliche Anzahl von Posts in Foren (Ausgabe alphabetisch sortiert nach Forumtitel)?
-- (12) Welche Personen sind mit der Person befreundet, die die meisten Likes auf einen Post bekommen hat? Sortieren Sie die Ausgabe alphabetisch nach dem Nachnamen.

