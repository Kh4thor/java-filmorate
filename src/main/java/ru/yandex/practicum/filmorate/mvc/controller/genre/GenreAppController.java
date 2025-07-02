package ru.yandex.practicum.filmorate.mvc.controller.genre;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import ru.yandex.practicum.filmorate.model.genre.Genre;

public interface GenreAppController {

	/*
	 * получить жанр по id
	 */
	Genre getGenre(@PathVariable int mpaId);

	/*
	 * получить список всех жанров
	 */
	List<Genre> getAllGenres();
}
