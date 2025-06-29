DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS films;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS mpa;
DROP TABLE IF EXISTS film_likes;
DROP TABLE IF EXISTS friends;

CREATE TABLE users (
    id INTEGER PRIMARY KEY,
    name VARCHAR(50),
    login VARCHAR(50),
    email VARCHAR(50),
    birthday DATE
);

CREATE TABLE genres (
	id INTEGER PRIMARY KEY,
	name VARCHAR(100)
);

CREATE TABLE mpa (
	id INTEGER PRIMARY KEY,
	name VARCHAR(100),
    description VARCHAR(255)
);

CREATE TABLE films (
    id INTEGER,
    name VARCHAR(50),
    description VARCHAR(255),
    release DATE,
    duration INTEGER,
    genre INTEGER,
    mpa INTEGER,
    FOREIGN KEY (genre) REFERENCES genres(id),
    FOREIGN KEY (mpa) REFERENCES mpa(id)
);

CREATE TABLE friends (
		user_id INTEGER,
		other_user_id INTEGER,
		user_status VARCHAR(50)
);

CREATE TABLE film_likes (
	film_id INTEGER,
	user_id INTEGER,
	like_status INTEGER
);