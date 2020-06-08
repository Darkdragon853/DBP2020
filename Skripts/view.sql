-- 2.a) Sichterstellung
-- Aufgabe: Die Freundschaftsbeziehung ist als gerichtete Beziehung gespeichert, um Anfragen bzgl. der Freundschaftsbeziehung komfortabel zu lösen sollen die Beziehungen ungerichtet gespeichert werden. Diesbezüglich sollen Sie eine Sicht “pkp_symmetric” erstellen, die beide Richtungen enthält.
-- Durch Prüfung der ersten zwei Einträge: Es existieren noch keine "echten Freunde", also das Paar a->b tauch nie über b->a auf.
-- Es soll also eine Sicht erstellt werden, in die alle Sachen aus person_knows_person gehören Plus die Werte andersrum
CREATE OR REPLACE VIEW pkp_symmetric AS SELECT person_1_id as Person1Id, person_2_id as Freund1Id, person_2_id as Person2Id, person_1_id as Freund2Id, creationdate FROM person_knows_person;
-- 2.b)

