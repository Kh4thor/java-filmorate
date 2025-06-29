package ru.yandex.practicum.filmorate.service.genre;

import java.util.List;

public interface GenreAppService<T> {

	/*
	 * получить жанр по id
	 */
	T getGenre(int mpaId);

	/*
	 * получить список всех жанров
	 */
	List<T> getAllGenres();

}
