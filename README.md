# Диаграмма базы данных для кинофильмов

## Сущности и связи

### users
- id (PK)
- name
- login
- email
- birthday

### mpa
- id (PK)
- name
- description

### genres
- id (PK)
- name

### films
- id (PK)
- name
- description
- release
- duration
- mpa (FK -> mpa.id)

### films_genres (связующая таблица для Many-to-Many между films и genres)
- film_id (FK -> films.id)
- genre_id (FK -> genres.id)

### friends
- user_one_id (FK -> users.id)
- user_two_id (FK -> users.id)
- user_two_status (BOOLEAN)

### films_likes
- film_id (FK -> films.id)
- user_id (FK -> users.id)
- like_status (BOOLEAN)

## Общее описание

- Таблица **users** хранит информацию о пользователях.
- Таблица **mpa** содержит рейтингные категории фильмов.
- Таблица **genres** — жанры фильмов.
- Таблица **films** — фильмы, связаны с MPA и жанрами.
- Таблица **films_genres** — реализует MANY-TO-MANY связь между фильмами и жанрами.
- Таблица **friends** — отображает дружеские связи между пользователями с учетом статуса.
- Таблица **films_likes** — хранит лайки и дизлайки пользователей для фильмов.