package ru.yandex.practicum.filmorate.mvc.storage.genre;

import java.util.List;

public interface GenreAppStorage<T> {
	/*
	 * получить жанр по id
	 */
	T getGenre(int genreId);

	/*
	 * получить список всех жанров
	 */
	List<T> getAllGenres();
}
