-- 2.b)
-- (1) In wie vielen verschiedenen afrikanischen Städten gibt es eine Universität? Done
SELECT COUNT(DISTINCT City.id) FROM (University JOIN City ON University.city_id = City.id JOIN Country ON City.country_id = Country.id JOIN Continent ON Country.continent_id = Continent.id) WHERE Continent.name = 'Africa';
-- Ergebnis: 100

-- (3) Wie viele Kommentare zu Posts gibt es aus jedem Land (Ausgabe aufsteigend sortiert nach Kommentaranzahl)? Die Liste soll auch Länder enthalten, für die keine Post-Kommentare existieren, d.h. die Kommentaranzahl = 0 ist! (Funktion Coalesce)
SELECT Country.name, COALESCE(COUNT(*), 0) AS NumberOfComments 
FROM (Comment JOIN Country on Comment.country_id = Country.id) 
WHERE Comment.reply_to_post_id IS NOT NULL 
GROUP BY Country.name 
ORDER BY 2 ASC;





-- (5) Mit wem ist ‘Hans Johansson’ befreundet? Done
SELECT P2.id AS FreundID, P2.firstName AS freundVorname, P2.lastName AS freundNachname FROM (person p2 LEFT JOIN pkp_symmetric ON p2.id = pkp_symmetric.freund1id) WHERE pkp_symmetric.person1id = (SELECT id FROM person P1 WHERE (P1.firstName = 'Hans') AND (P1.lastName = 'Johansson'));
--Ergbenis: 9

-- (7) Welche Nutzer sind Mitglied in allen Foren, in denen auch ‘Mehmet Koksal’ Mitglied ist (Angabe Name)?
-- first get all Forum ids where Mehmet Koksal is in
SELECT * FROM (
    SELECT forum.id FROM 
        (forum JOIN forum_hasMember_person ON forum.id = forum_hasMember_person.forum_id JOIN person ON forum_hasMember_person.person_id = person.id)
        WHERE (person.firstName = 'Mehmet') AND (person.lastName = 'Koksal'))
        AS MehmetsForums;

-- then get all users with that.
SELECT * FROM (Person JOIN forum_hasMember_person ON person.id = forum_hasMember_person.person_id JOIN Forum ON forum_hasMember_person.forum_id = forum.id) GROUP BY person.id;



-- (9) Zu welchen Themen (‘tag classes’) gibt es die meisten Posts? Geben Sie die Namen der Top 10 ‘tag classes’ mit ihrer Häufigkeit aus! Done.
SELECT tagclass.name, tagclass.id, temp.anzahl 
    FROM (
        SELECT COUNT(pht1.post_id) AS anzahl, tht1.tagclass_id  
            FROM post_hastag_tag pht1 JOIN tag_hastype_tagclass tht1 ON pht1.tag_id = tht1.tag_id 
            GROUP BY tht1.tagclass_id 
            ORDER BY anzahl DESC 
            LIMIT 10)
        AS temp 
        JOIN tagclass ON temp.tagclass_id = tagclass.id 
        ORDER BY temp.anzahl DESC;




-- (11) Welche Foren enthalten mehr Posts als die durchschnittliche Anzahl von Posts in Foren (Ausgabe alphabetisch sortiert nach Forumtitel)? Done.
-- zuerst die durchschnittliche Anzahl von Posts in Foren
SELECT AVG(ForumCounts.anzahl) FROM (SELECT COUNT(p1.id) AS anzahl FROM post p1 JOIN forum f1 ON p1.forum_id = f1.id GROUP BY f1.id) AS ForumCounts;
-- dann alle die mehr enthalten
SELECT ForumswithCounts.title, ForumswithCounts.anzahl FROM (SELECT F2.title, COUNT(P2.id) AS anzahl FROM Forum F2 JOIN Post P2 ON F2.id = P2.forum_id GROUP BY F2.id) AS ForumswithCounts WHERE ForumswithCounts.anzahl > (SELECT AVG(ForumCounts.anzahl) FROM (SELECT COUNT(p1.id) AS anzahl FROM post p1 JOIN forum f1 ON p1.forum_id = f1.id GROUP BY f1.id) AS ForumCounts) ORDER BY ForumswithCounts.title;


-- (12) Welche Personen sind mit der Person befreundet, die die meisten Likes auf einen Post bekommen hat? Sortieren Sie die Ausgabe alphabetisch nach dem Nachnamen. Done.
-- zuerst die Person holen welche die meisten Likes bekommen hat
SELECT tempo.author_id 
FROM (
        SELECT COUNT (p2.id) as anzahl, p2.author_id 
        FROM post p2 JOIN person_likes_post plp2 ON p2.id = plp2.post_id 
        GROUP BY p2.author_id) AS tempo 
WHERE tempo.anzahl = (
        SELECT MAX(temp.anzahl)
        FROM (
                SELECT COUNT(p1.id) AS anzahl, p1.author_id 
                FROM post p1 JOIN person_likes_post plp1 ON p1.id = plp1.post_id 
                GROUP BY p1.author_id) AS temp);



-- dann zugehörige Freunde finden
SELECT person.lastName, person.firstName, friends.person1id 
FROM (
        SELECT pkp1.person1id, pkp1.freund1id, pkp1.creationDate 
        FROM pkp_symmetric pkp1 
        WHERE pkp1.person1id = (
                SELECT tempo.author_id 
                FROM (
                        SELECT COUNT (p2.id) as anzahl, p2.author_id 
                        FROM post p2 JOIN person_likes_post plp2 ON p2.id = plp2.post_id 
                        GROUP BY p2.author_id) AS tempo 
                WHERE tempo.anzahl = (
                        SELECT MAX(temp.anzahl)
                        FROM (
                                SELECT COUNT(p1.id) AS anzahl, p1.author_id 
                                FROM post p1 JOIN person_likes_post plp1 ON p1.id = plp1.post_id 
                                GROUP BY p1.author_id) AS temp
                )
        )
) AS friends JOIN person ON friends.freund1id = person.id 
ORDER BY person.lastName;
-- Ergebnis: 
-- Achiou   | Ali       | 2199023255611
-- Bravo    | Adrian    | 2199023255611
-- Chen     | Cheng     | 2199023255611
-- Davies   | Bryn      | 2199023255611
-- Li       | Jie       | 2199023255611
-- Liu      | Chong     | 2199023255611
-- Loan     | Cam       | 2199023255611
-- Oliveira | Celso     | 2199023255611
-- Zhang    | Zhi       | 2199023255611
