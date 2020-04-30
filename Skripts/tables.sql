-- Zuerst kümmern wir uns um die sicheren Entitäten
-- Elternklassen werden nicht in das Relationenschema übernommen
-- Kindklassen sollen in eigene Relationen überführt werden



-- Tabelle Person

/*
create table person(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    creationDate TIMESTAMP NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    gender VARCHAR(7) NOT NULL,
    birthday Date NOT NULL,
    email VARCHAR[] NOT NULL, -- ArrayType bc [1..*]
    speaks VARCHAR[] NOT NULL, -- ArrayType bc [1..*]
    browserUsed VARCHAR(20) NOT NULL,
    locationIP VARCHAR(15) NOT NULL
);


-- Tabelle Tag
create table tag(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Tabelle TagClass 
create table tagclass(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);


-- Tabelle City
create table city(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Tabelle Country
create table country(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Tabelle Continent
create table continent(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);


-- Tabelle Company
create table company(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);


-- Tabelle University
create table university(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);


-- Tabelle Forum
create table forum(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    creationDate TIMESTAMP NOT NULL -- erstmal ohne Zeitzone, Daten müssen entsprechend geparsed werden
);


-- Tabelle Post
create table post(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    language VARCHAR(50), -- Achtung, hier soll Null erlaubt sein
    imageFile VARCHAR(100), -- Achtung, hier soll Null erlaubt sein
    creationDate TIMESTAMP NOT NULL, -- erstmal ohne Zeitzone, Daten müssen entsprechend geparsed werden
    browserUsed VARCHAR(20) NOT NULL,
    locationIP VARCHAR(15) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL
    -- gehört zu nem Forum also packen wir besser die id später noch rein
);


-- Tabelle Comment
create table comment(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    creationDate TIMESTAMP NOT NULL, -- erstmal ohne Zeitzone, Daten müssen entsprechend geparsed werden
    browserUsed VARCHAR(20) NOT NULL,
    locationIP VARCHAR(15) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL
);
*/


-- Als nächstes kümmern wir uns um die verschiedenen Beziehungen

/* Forum_containerOf_Post würde ich als 1:n sehen und daher den Fremdschlüssel des Forums in den Post reinpacken. UNIQUE brauchen wir nicht, da mehrere Posts zu einem Forum gehören können

create table post(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    language VARCHAR(50), -- Achtung, hier soll Null erlaubt sein
    imageFile VARCHAR(100), -- Achtung, hier soll Null erlaubt sein
    creationDate TIMESTAMP NOT NULL, -- erstmal ohne Zeitzone, Daten müssen entsprechend geparsed werden
    browserUsed VARCHAR(20) NOT NULL,
    locationIP VARCHAR(15) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL,
    forum_id BIGINT NOT NULL REFERENCES forum(id),
);
*/ 

/* Message_hasCreator_Person: Jede Message hat genau einen Autor. Also muss die ID des Autors in die Message-Tabellen, die sich in Comment und Post aufsplitten. UNIQUE ist wieder nicht
erforderlich, da ein Autor mehrere Messages erstellen kann

create table comment(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    creationDate TIMESTAMP NOT NULL, -- erstmal ohne Zeitzone, Daten müssen entsprechend geparsed werden
    browserUsed VARCHAR(20) NOT NULL,
    locationIP VARCHAR(15) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL,
    author_id BIGINT NOT NULL REFERENCES person(id)
);

create table post(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    language VARCHAR(50), -- Achtung, hier soll Null erlaubt sein
    imageFile VARCHAR(100), -- Achtung, hier soll Null erlaubt sein
    creationDate TIMESTAMP NOT NULL, -- erstmal ohne Zeitzone, Daten müssen entsprechend geparsed werden
    browserUsed VARCHAR(20) NOT NULL,
    locationIP VARCHAR(15) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL,
    forum_id BIGINT NOT NULL REFERENCES forum(id),
    author_id BIGINT NOT NULL REFERENCES person(id)
);
*/

/* Forum_hasMember_Person: Ein Forum hat beliebig viele Mitglieder und eine Person kann in beliebig vielen Foren sein -> neue Tabelle

create table forum_hasMember_person(
    person_id BIGINT NOT NULL REFERENCES person(id),
    forum_id BIGINT  NOT NULL REFERENCES forum(id),
    joinDate TIMESTAMP NOT NULL
);
*/



/* Forum_hasModerator_Person: Eine Person kann in einem Forum Moderator sein - ein Forum hat immer einen Moderator -> Einfach den PrimaryKey von Person zum Forum mit rein

create table forum(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    creationDate TIMESTAMP NOT NULL, -- erstmal ohne Zeitzone, Daten müssen entsprechend geparsed werden
    moderator BIGINT NOT NULL REFERENCES person(id),
    UNIQUE (moderator) -- eine Person kann nur in einem oder keinem Forum Moderator sein
);
*/



/* Forum_hasTag_Tag: m:n-Beziehung: Tags können in beliebigen Foren auftachen, sowie Foren beliebig viele Tags haben können -> neue Tabelle, keine Unique Constraints

create table forum_hasTag_tag(
    forum_id BIGINT NOT NULL REFERENCES forum(id),
    tag_id BIGINT NOT NULL REFERENCES tag(id)
);
*/



/* Message_hasTag_Tag: wieder m:n Beziehung, ein Tag kann zu beliebig vielen Messages gehören und eine Message kann beliebig viele Tags haben
Allerdings haben wir die beiden Kindklassen implementiert also brauchen wir zwei neue Tabellen:

create table comment_hasTag_tag(
    comment_id BIGINT NOT NULL REFERENCES comment(id),
    tag_id BIGINT NOT NULL REFERENCES tag(id)
);

create table post_hasTag_tag(
    post_id BIGINT NOT NULL REFERENCES post(id),
    tag_id BIGINT NOT NULL REFERENCES tag(id)
);
*/



/* Tag_hasType_TagClass: erneut m:n Beziehung laut UML -> neue Tabelle aber keine UNIQUE Constraints

create table tag_hasType_tagclass(
    tag_id BIGINT NOT NULL REFERENCES tag(id),
    tagclass_id BIGINT NOT NULL REFERENCES tagclass(id),
);
*/



/* TagClass_isSubclassOf_TagClass: weil wir es ja noch nicht hatten, erneut m:n -> neue Tabelle

create table tagclass_isSubclassOf_tagclass(
    tag_parent_id BIGINT NOT NULL REFERENCES tag(id),
    tag_child_id BIGINT NOT NULL REFERENCES tag(id)
);
*/



/* Company_isLocatedIn_Country: eine Firma ist immer in genau einem Land -> PrimaryKey vom Land zur Firma, aber nicht UNIQUE da mehrere Firmen in einem Land sein können

create table company(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country_id BIGINT NOT NULL REFERENCES country(id)
);
*/



/* University_isLocatedIn_City: Exakt gleich wie davor, nur das eine Uni in genau einer Stadt steht:

create table university(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    city_id BIGINT NOT NULL REFERENCES city(id)
);
*/



/* Person_isLocatedIn_City: ähnlich zu den Beziehungen davor, eine Person ist immer in genau einer Stadt:

create table person(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    gender VARCHAR(7) NOT NULL,
    birthday Date NOT NULL,
    email VARCHAR[] NOT NULL, -- ArrayType bc [1..*]
    speaks VARCHAR[] NOT NULL, -- ArrayType bc [1..*]
    browserUsed VARCHAR(20) NOT NULL,
    locationIP VARCHAR(15) NOT NULL,
    city_id BIGINT NOT NULL REFERENCES city(id)
);
*/



/* Message_isLocatedIn_Country: auch eine Message hat eine N:1 Beziehung zu Country. Da wir zwei Kindklassen implementiert haben, müssen beide angepasst werden: (PKEY von Country rein)

create table comment(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    creationDate TIMESTAMP NOT NULL, -- erstmal ohne Zeitzone, Daten müssen entsprechend geparsed werden
    browserUsed VARCHAR(20) NOT NULL,
    locationIP VARCHAR(15) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL,
    author_id BIGINT NOT NULL REFERENCES person(id),
    country_id BIGINT NOT NULL REFERENCES country(id)
);

create table post(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    language VARCHAR(50), -- Achtung, hier soll Null erlaubt sein
    imageFile VARCHAR(100), -- Achtung, hier soll Null erlaubt sein
    creationDate TIMESTAMP NOT NULL, -- erstmal ohne Zeitzone, Daten müssen entsprechend geparsed werden
    browserUsed VARCHAR(20) NOT NULL,
    locationIP VARCHAR(15) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL,
    forum_id BIGINT NOT NULL REFERENCES forum(id),
    author_id BIGINT NOT NULL REFERENCES person(id),
    country_id BIGINT NOT NULL REFERENCES country(id)
);
*/



/* Person_knows_Person: diese belastenden m:n Beziehungen... xD -> neue Tabelle lul

create table person_knows_person(
    person_1_id BIGINT NOT NULL REFERENCES person(id),
    person_2_id BIGINT NOT NULL REFERENCES person(id),
    creationDate TIMESTAMP NOT NULL
);
*/



/* Person_likes_Message: ist wieder ne M:N Beziehung mit den beiden Kindklassen und einem extra Attribut -> zwei neue Tabellen

create table person_likes_post(
    person_id BIGINT NOT NULL REFERENCES person(id),
    post_id BIGINT NOT NULL REFERENCES post(id),
    creationDate TIMESTAMP NOT NULL
);

create table person_likes_comment(
    person_id BIGINT NOT NULL REFERENCES person(id),
    comment_id BIGINT NOT NULL REFERENCES comment(id),
    creationDate TIMESTAMP NOT NULL
);
*/



/* Comment_replyOf_Message: Ein Comment ist immer eine Antwort auf genau eine andere Message. Also muss entweder ein Schlüssel von Comment oder Post mit rein, würde das über ne Check-Konstante lösen

create table comment(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    creationDate TIMESTAMP NOT NULL, -- erstmal ohne Zeitzone, Daten müssen entsprechend geparsed werden
    browserUsed VARCHAR(20) NOT NULL,
    locationIP VARCHAR(15) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL,
    author_id BIGINT NOT NULL REFERENCES person(id),
    country_id BIGINT NOT NULL REFERENCES country(id),
    reply_to_post_id BIGINT REFERENCES post(id),
    reply_to_comment_id BIGINT REFERENCES comment(id),
    CHECK (reply_to_comment_id NOT NULL) OR (reply_to_post_id NOT NULL) -- noch schauen ob das so geht
);
*/



/* Person_studyAt_University: m:n Beziehung, da eine Person an mehrere Unis studieren kann und ne Uni beliebig viele Studenten hat -> neue Tabelle 

create table person_studyAt_university(
    person_id BIGINIT NOT NULL REFERENCES person(id),
    university_id BIGINIT NOT NULL REFERENCES university(id),
    classYear INT NOT NULL
);
*/



/* Person_workAt_Company: siehe Person_studyAt_University. Gleiche Rechtfertigung -> neue Tabelle

create table person_workAt_company(
    person_id BIGINIT NOT NULL REFERENCES person(id),
    company_id BIGINT NOT NULL REFERENCES company(id),
    workFrom INT NOT NULL
);
*/
---------------------------------------------------------------- Ende der Beziehungen die auf der Website aufgeführt werden

-- Die Person_hasInterest_Tag Beziehung wird nicht erwähnt?!? Genauso wie die IsPartOf Beziehungen:
-->

/* Person_hasInterest_Tag: m:n Beziehung, neue Tabelle ->

create table person_hasInterest_Tag(
    person_id BIGINT NOT NULL REFERENCES person(id),
    tag_id BIGINT NOT NULL REFERENCES tag(id)
);
*/



/* City_isPartOf_Country: n:1, also PKey aus Country bei City mit rein

create table city(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    country_id BIGINT NOT NULL REFERENCES country(id)
);
*/



/* Country_isPartOf_Continent: n:1, also Pkey aus Continent bei City mit rein

create table country(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    continent_id BIGINT NOT NULL REFERENCES continent(id)
);
*/



---
---
---  RESULTIERENDE TABELLEN:
---
---




-- Funktion valid_email, checkt ein Varchar auf Validität der Email-Adresse
CREATE FUNCTION valid_email(b boolean, v VARCHAR) 
    RETURNS boolean
    AS $$ 
    SELECT $2 ~ '^[\w\.]+@[\w+\.]+\.[\w]{2,4}$' as result $$
    LANGUAGE sql;

-- Operator =%= wird für die Email-Constraint gebraucht um alle Elemente aus dem Array zu vergleichen
CREATE OPERATOR =%= (
    PROCEDURE = valid_email,
    LEFTARG = boolean,
    RIGHTARG = varchar
);

-- Tabelle Tag
create table tag(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL
);


-- Tabelle TagClass 
create table tagclass(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL
);


-- Tabelle Continent
create table continent(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL

    -- UNIQUE(name) einfach weil wir es können
);


-- Tabelle Country
create table country(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    continent_id BIGINT NOT NULL REFERENCES continent(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Tabelle City
create table city(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country_id BIGINT NOT NULL REFERENCES country(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Tabelle Person
create table person(
    id BIGSERIAL PRIMARY KEY,
    creationDate TIMESTAMP NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    gender VARCHAR(7) NOT NULL,
    birthday Date NOT NULL,
    email VARCHAR[] NOT NULL, -- ArrayType bc [1..*]
    speaks VARCHAR[] NOT NULL, -- ArrayType bc [1..*]
    browserUsed VARCHAR(50) NOT NULL,
    locationIP VARCHAR(40) NOT NULL,
    city_id BIGINT NOT NULL REFERENCES city(id) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT birthday_not_in_future CHECK (birthday <= NOW()::DATE),
    CONSTRAINT vaild_email CHECK (TRUE =%= ALL(email))
);


-- Tabelle Company
create table company(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    country_id BIGINT NOT NULL REFERENCES country(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Tabelle University
create table university(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    city_id BIGINT NOT NULL REFERENCES city(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Tabelle Forum
create table forum(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    creationDate TIMESTAMP NOT NULL, -- erstmal ohne Zeitzone, Daten müssen entsprechend geparsed werden
    moderator BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (moderator) -- eine Person kann nur in einem oder keinem Forum Moderator sein
);


-- Tabelle Post
create table post(
    id BIGSERIAL PRIMARY KEY,
    language VARCHAR(2), -- Achtung, hier soll Null erlaubt sein
    imageFile VARCHAR(150), -- Achtung, hier soll Null erlaubt sein
    creationDate TIMESTAMP NOT NULL, -- erstmal ohne Zeitzone, Daten müssen entsprechend geparsed werden
    browserUsed VARCHAR(50) NOT NULL,
    locationIP VARCHAR(40) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL,
    forum_id BIGINT NOT NULL REFERENCES forum(id) ON DELETE CASCADE ON UPDATE CASCADE,
    author_id BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE,
    country_id BIGINT NOT NULL REFERENCES country(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Tabelle Comment
create table comment(
    id BIGSERIAL PRIMARY KEY,
    creationDate TIMESTAMP NOT NULL, -- erstmal ohne Zeitzone, Daten müssen entsprechend geparsed werden
    browserUsed VARCHAR(50) NOT NULL,
    locationIP VARCHAR(40) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL,
    author_id BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE,
    country_id BIGINT NOT NULL REFERENCES country(id) ON DELETE CASCADE ON UPDATE CASCADE,
    reply_to_post_id BIGINT REFERENCES post(id) ON DELETE CASCADE ON UPDATE CASCADE,
    reply_to_comment_id BIGINT REFERENCES comment(id) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT belongs_to_message_or_post CHECK (((reply_to_comment_id IS NOT NULL) AND (reply_to_post_id IS NULL)) OR ((reply_to_comment_id IS NULL) AND (reply_to_post_id IS NOT NULL))) -- noch schauen ob das so geht, besser XOR!
);


-- Tabelle Forum_hasMember_Person
create table forum_hasMember_person( -- muss mindestens ein Member drin sein, sonst ist das kein gültiges Forum
    person_id BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE,
    forum_id BIGINT NOT NULL REFERENCES forum(id) ON DELETE CASCADE ON UPDATE CASCADE,
    joinDate TIMESTAMP NOT NULL,
    PRIMARY KEY (person_id, forum_id)
);


-- Tabelle Forum_hasTag_Tag
create table forum_hasTag_tag(
    forum_id BIGINT NOT NULL REFERENCES forum(id) ON DELETE CASCADE ON UPDATE CASCADE,
    tag_id BIGINT NOT NULL REFERENCES tag(id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (forum_id, tag_id)
);


-- Tabelle Tag_hasType_TagClass
create table tag_hasType_tagclass(
    tag_id BIGINT NOT NULL REFERENCES tag(id) ON DELETE CASCADE ON UPDATE CASCADE,
    tagclass_id BIGINT NOT NULL REFERENCES tagclass(id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (tag_id, tagclass_id)
);


-- Tabelle TagClass_isSubclassOf_TagClass
create table tagclass_isSubclassOf_tagclass(
    tag_parent_id BIGINT NOT NULL REFERENCES tag(id) ON DELETE CASCADE ON UPDATE CASCADE,
    tag_child_id BIGINT NOT NULL REFERENCES tag(id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (tag_parent_id, tag_child_id)
);


-- Tabelle Post_hasTag_Tag
create table post_hasTag_tag(
    post_id BIGINT NOT NULL REFERENCES post(id) ON DELETE CASCADE ON UPDATE CASCADE,
    tag_id BIGINT NOT NULL REFERENCES tag(id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (post_id, tag_id)
);


-- Tabelle Comment_hasTag_Tag
create table comment_hasTag_tag(
    comment_id BIGINT NOT NULL REFERENCES comment(id) ON DELETE CASCADE ON UPDATE CASCADE,
    tag_id BIGINT NOT NULL REFERENCES tag(id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (comment_id, tag_id)
);


-- Tabelle Person_knows_Person
create table person_knows_person(
    person_1_id BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE,
    person_2_id BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE,
    creationDate TIMESTAMP NOT NULL,
    PRIMARY KEY (person_1_id, person_2_id)
);


-- Tabelle Person_studyAt_University
create table person_studyAt_university(
    person_id BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE,
    university_id BIGINT NOT NULL REFERENCES university(id) ON DELETE CASCADE ON UPDATE CASCADE,
    classYear INT NOT NULL,
    PRIMARY KEY (person_id, university_id)
);


-- Tabelle Person_workAt_Company
create table person_workAt_company(
    person_id BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE,
    company_id BIGINT NOT NULL REFERENCES company(id) ON DELETE CASCADE ON UPDATE CASCADE,
    workFrom INT NOT NULL,
    PRIMARY KEY (person_id, company_id)
);


-- Tabelle Person_likes_Post
create table person_likes_post(
    person_id BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE,
    post_id BIGINT NOT NULL REFERENCES post(id) ON DELETE CASCADE ON UPDATE CASCADE,
    creationDate TIMESTAMP NOT NULL,
    PRIMARY KEY (person_id, post_id)
);


-- Tabelle Person_likes_Comment
create table person_likes_comment(
    person_id BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE,
    comment_id BIGINT NOT NULL REFERENCES comment(id) ON DELETE CASCADE ON UPDATE CASCADE,
    creationDate TIMESTAMP NOT NULL,
    PRIMARY KEY (person_id, comment_id)
);


-- Tabelle Person_hasInterest_Tag
create table person_hasInterest_Tag(
    person_id BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE,
    tag_id BIGINT NOT NULL REFERENCES tag(id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (person_id, tag_id)
);





-- bisherige Implementierung wurde getested, Postgres nimmt das so an.
-- Todo: Update- und Löschregeln, Schlüsselconstraints, Datentypen prüfen

-- Was ist mit Ländern, die auf mehreren Kontinenten liegen?
-- Macht es Sinn, wenn eine Firma keine Mitarbeiter hat bzw eine Universität keine Studenten?
