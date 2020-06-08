-- 2.a) Sichterstellung
-- Aufgabe: Die Freundschaftsbeziehung ist als gerichtete Beziehung gespeichert, um Anfragen bzgl. der Freundschaftsbeziehung komfortabel zu lösen sollen die Beziehungen ungerichtet gespeichert werden. Diesbezüglich sollen Sie eine Sicht “pkp_symmetric” erstellen, die beide Richtungen enthält.
-- Durch Prüfung der ersten zwei Einträge: Es existieren noch keine "echten Freunde", also das Paar a->b tauch nie über b->a auf.
-- Es soll also eine Sicht erstellt werden, in die alle Sachen aus person_knows_person gehören Plus die Werte andersrum
CREATE OR REPLACE VIEW pkp_symmetric AS SELECT person_1_id as Person1Id, person_2_id as Freund1Id, person_2_id as Person2Id, person_1_id as Freund2Id, creationdate FROM person_knows_person;
-- 2.b)

-- (1) In wie vielen verschiedenen afrikanischen Städten gibt es eine Universität?
 
SELECT * FROM University where city_id = (SELECT );



-- (3) Wie viele Kommentare zu Posts gibt es aus jedem Land (Ausgabe aufsteigend sortiert nach Kommentaranzahl)? Die Liste soll auch Länder enthalten, für die keine Post-Kommentare existieren, d.h. die Kommentaranzahl = 0 ist! (Funktion Coalesce)
 
-- (5) Mit wem ist ‘Hans Johansson’ befreundet?
 
-- (7) Welche Nutzer sind Mitglied in allen Foren, in denen auch ‘Mehmet Koksal’ Mitglied ist (Angabe Name)?
 
-- (9) Zu welchen Themen (‘tag classes’) gibt es die meisten Posts? Geben Sie die Namen der Top 10 ‘tag classes’ mit ihrer Häufigkeit aus!

-- (11) Welche Foren enthalten mehr Posts als die durchschnittliche Anzahl von Posts in Foren (Ausgabe alphabetisch sortiert nach Forumtitel)?
-- (12) Welche Personen sind mit der Person befreundet, die die meisten Likes auf einen Post bekommen hat? Sortieren Sie die Ausgabe alphabetisch nach dem Nachnamen.

