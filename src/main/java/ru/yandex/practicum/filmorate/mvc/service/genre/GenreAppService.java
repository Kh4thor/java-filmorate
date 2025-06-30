package ru.yandex.practicum.filmorate.mvc.service.genre;

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
