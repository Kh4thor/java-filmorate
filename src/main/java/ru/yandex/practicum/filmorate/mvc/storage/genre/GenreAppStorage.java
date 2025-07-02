package ru.yandex.practicum.filmorate.mvc.storage.genre;

import java.util.List;

import ru.yandex.practicum.filmorate.model.genre.Genre;

public interface GenreAppStorage {
	/*
	 * получить жанр по id
	 */
	Genre getGenre(int genreId);

	/*
	 * получить список всех жанров
	 */
	List<Genre> getAllGenres();

	/*
	 * проверить наличие жанра в базе
	 */
	boolean isGenreExist(int genreId);
}
