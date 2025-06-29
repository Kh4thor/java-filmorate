package ru.yandex.practicum.filmorate.controller.genre;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

public interface GenreAppController<T> {

	/*
	 * получить жанр по id
	 */
	T getGenre(@PathVariable int mpaId);

	/*
	 * получить список всех жанров
	 */
	List<T> getAllGenres();
}
