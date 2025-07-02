DROP TABLE IF EXISTS films_genres;
DROP TABLE IF EXISTS films_likes;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS films;
DROP TABLE IF EXISTS mpa;
DROP TABLE IF EXISTS users, friends CASCADE;

CREATE TABLE mpa (
	id INTEGER PRIMARY KEY,
	name VARCHAR(100),
    description VARCHAR(255)
);

CREATE TABLE genres (
	id INTEGER PRIMARY KEY,
	name VARCHAR(100)
);

CREATE TABLE users (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(50),
    login VARCHAR(50),
    email VARCHAR(50),
    birthday DATE
);

CREATE TABLE films (
    id  IDENTITY PRIMARY KEY,
    name VARCHAR(50),
    description VARCHAR(255),
    release DATE,
    duration INTEGER,
    mpa INTEGER,
    FOREIGN KEY (mpa) REFERENCES mpa(id)
);

CREATE TABLE films_genres (
	film_id INTEGER,
	genre_id INTEGER,
	FOREIGN KEY (film_id) REFERENCES films(id),
	FOREIGN KEY (genre_id) REFERENCES genres(id)
);

CREATE TABLE friends (
	user_one_id INTEGER,
	user_two_id INTEGER,
	user_two_status BOOLEAN,
	FOREIGN KEY (user_one_id) REFERENCES users(id),
	FOREIGN KEY (user_two_id) REFERENCES users(id)
);

CREATE TABLE films_likes (
	film_id INTEGER,
	user_id INTEGER,
	like_status BOOLEAN,
	FOREIGN KEY (film_id) REFERENCES films(id),
	FOREIGN KEY (user_id) REFERENCES users(id)
);