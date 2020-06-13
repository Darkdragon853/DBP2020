
---
---
---  RESULTIERENDE TABELLEN:
---
---

-- Funktion valid_email, checkt ein Varchar auf Validität der Email-Adresse
CREATE FUNCTION valid_email(b boolean, v VARCHAR) 
    RETURNS boolean
    AS $$ 
    SELECT $2 ~ '^[\w\.-]+@[\w+\.-]+\.[\w]{2,4}$' as result $$
    LANGUAGE sql;


CREATE OPERATOR =%= (
    PROCEDURE = valid_email,
    LEFTARG = boolean,
    RIGHTARG = varchar
);


-- Tabelle Tag
create table tag(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    url TEXT
);


-- Tabelle TagClass 
create table tagclass(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    url TEXT
);


-- Tabelle Continent
create table continent(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    url TEXT
);


-- Tabelle Country
create table country(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    continent_id BIGINT NOT NULL REFERENCES continent(id) ON DELETE CASCADE ON UPDATE CASCADE,
    url TEXT
);


-- Tabelle City
create table city(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country_id BIGINT NOT NULL REFERENCES country(id) ON DELETE CASCADE ON UPDATE CASCADE,
    url TEXT
);


-- Tabelle Person
create table person(
    id BIGSERIAL PRIMARY KEY,
    creationDate TIMESTAMP NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    gender VARCHAR(7) NOT NULL,
    birthday Date NOT NULL,
    browserUsed VARCHAR(50) NOT NULL,
    locationIP VARCHAR(40) NOT NULL,
    city_id BIGINT NOT NULL REFERENCES city(id) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT birthday_not_in_future CHECK (birthday <= NOW()::DATE)
);


-- Tabelle Company
create table company(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    url TEXT,
    country_id BIGINT NOT NULL REFERENCES country(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Tabelle University
create table university(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    url TEXT,
    city_id BIGINT NOT NULL REFERENCES city(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Tabelle Forum
create table forum(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    creationDate TIMESTAMP NOT NULL, 
    moderator BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Tabelle Post
create table post(
    id BIGSERIAL PRIMARY KEY,
    language VARCHAR(2), -- Achtung, hier soll Null erlaubt sein
    imageFile VARCHAR(150), -- Achtung, hier soll Null erlaubt sein
    creationDate TIMESTAMP NOT NULL, 
    browserUsed VARCHAR(50) NOT NULL,
    locationIP VARCHAR(40) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL,
    forum_id BIGINT NOT NULL REFERENCES forum(id) ON DELETE CASCADE ON UPDATE CASCADE,
    author_id BIGINT REFERENCES person(id) ON DELETE SET NULL ON UPDATE CASCADE,
    country_id BIGINT NOT NULL REFERENCES country(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Tabelle Comment
create table comment(
    id BIGSERIAL PRIMARY KEY,
    creationDate TIMESTAMP NOT NULL, 
    browserUsed VARCHAR(50) NOT NULL,
    locationIP VARCHAR(40) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL,
    author_id BIGINT REFERENCES person(id) ON DELETE SET NULL ON UPDATE CASCADE,
    country_id BIGINT NOT NULL REFERENCES country(id) ON DELETE CASCADE ON UPDATE CASCADE,
    reply_to_post_id BIGINT REFERENCES post(id) ON DELETE SET NULL ON UPDATE CASCADE,
    reply_to_comment_id BIGINT REFERENCES comment(id) ON DELETE SET NULL ON UPDATE CASCADE,

    CONSTRAINT belongs_to_message_or_post CHECK (((reply_to_comment_id IS NOT NULL) AND (reply_to_post_id IS NULL)) OR ((reply_to_comment_id IS NULL) AND (reply_to_post_id IS NOT NULL))) -- noch schauen ob das so geht, besser XOR!
);


-- Tabelle Forum_hasMember_Person
create table forum_hasMember_person( 
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

-- ArrayType bc [1..*]
-- Tabelle Person_has_Email
create table person_has_Email(
    person_id BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE,
    email VARCHAR(200) NOT NULL,
    PRIMARY KEY (person_id, email),

    CONSTRAINT vaild_email CHECK (TRUE =%= email)
);

-- ArrayType bc [1..*]
-- Tabelle Person_speaks_language
create table person_speaks_language(
    person_id BIGINT NOT NULL REFERENCES person(id) ON DELETE CASCADE ON UPDATE CASCADE,
    language VARCHAR(10) NOT NULL,
    PRIMARY KEY(person_id, language)
);