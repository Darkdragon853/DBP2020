-- Zuerst kümmern wir uns um die sicheren Entitäten

-- Tabelle Person
create table person(
    id BIGSERIAL NOT NULL PRIMARY KEY,
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

-- Tabelle TagClass -- nicht sicher, ob das wirklich ne eigenständige Relation ist
create table tagclass(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);


-- Tabelle City
create table city(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Tabelle Cuntry
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
    name VARCHAR(100)
);


-- Tabelle University
create table university(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100)
);


-- Tabelle Forum
create table forum(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    creationDate TIMESTAMP NOT NULL -- mit oder ohne Zeitzone? Steht im Forum.csv mit + 0000 drin
);


-- Tabelle Post
create table post(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    language VARCHAR(50), -- Achtung, hier soll Null erlaubt sein
    imageFile VARCHAR(100), -- Achtung, hier soll Null erlaubt sein
    creationDate TIMESTAMP NOT NULL, -- mit oder ohne Zeitzone? Steht im Forum.csv mit + 0000 drin
    browserUsed VARCHAR(20) NOT NULL,
    locationIP VARCHAR(15) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL
    -- gehört zu nem Forum also packen wir besser die id später noch rein
);


-- Tabelle Comment
create table comment(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    creationDate TIMESTAMP NOT NULL, -- mit oder ohne Zeitzone? Steht im Forum.csv mit + 0000 drin
    browserUsed VARCHAR(20) NOT NULL,
    locationIP VARCHAR(15) NOT NULL,
    content TEXT, -- Achtung, hier soll Null erlaubt sein
    length INT NOT NULL
);

-- bisherige Implementierung wurde getested, Postgres nimmt das so an.

-- Tabelle Place -- ist denk ich mal keine eigenständige Relation, sollte aber nochmal nachschauen wie Vererbung und Interfaces gehandhabt werden
-- Tabelle Message und Tabelle Organization -> denk ich mal gilt das gleiche
-- mehr Infos: http://btw2017.informatik.uni-stuttgart.de/slidesandpapers/F-17-74/paper_web.pdf


-- Es bietet sich an, Universität und Company "virtuell" zu fragmentieren -> Also ihre Attribute in "Organisation" zu schieben und ein typ-Attribut einzuführen und die University-Tabelle und Company-Tabelle zu dropen
-- Place, Continent, Country und City werden in den Dateien genauso gehandhabt: Nur Places mit typ-Attribut und isPartOf Attribut

-- Comment und Post würde ich so lassen, und jeweils die Attribute aus "Message" übernehmen
