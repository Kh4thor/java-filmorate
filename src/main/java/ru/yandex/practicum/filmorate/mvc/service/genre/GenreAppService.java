package ru.yandex.practicum.filmorate.mvc.service.genre;

import java.util.List;

import ru.yandex.practicum.filmorate.model.genre.Genre;

public interface GenreAppService {

	/*
	 * получить жанр по id
	 */
	Genre getGenre(int mpaId);

	/*
	 * получить список всех жанров
	 */
	List<Genre> getAllGenres();

}
