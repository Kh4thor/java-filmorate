DROP TABLE IF EXISTS users, genres, mpa, films, films_genres, friends, film_likes;

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
    id INTEGER PRIMARY KEY,
    name VARCHAR(50),
    description VARCHAR(255),
    release DATE,
    duration INTEGER,
    genre INTEGER,
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
		user_status VARCHAR(50)
);

CREATE TABLE film_likes (
	film_id INTEGER,
	user_id INTEGER,
	like_status BOOLEAN
);