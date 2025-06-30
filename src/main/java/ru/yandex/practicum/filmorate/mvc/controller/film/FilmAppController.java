package ru.yandex.practicum.filmorate.mvc.controller.film;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import ru.yandex.practicum.filmorate.model.film.Film;

public interface FilmAppController<T> {

	/*
	 * создать или обновить фильм
	 */
	Film createOrUpdateFilm(T t);

	/*
	 * удалить фильм по id
	 */
	void deleteFilm(Long id);

	/*
	 * удалить все фильмы
	 */
	void deleteAllFilms();

	/*
	 * получить фильмы по id
	 */
	T getFilm(@PathVariable Long id);

	/*
	 * получить список всех фильмов
	 */
	List<T> getAllFilms();
}