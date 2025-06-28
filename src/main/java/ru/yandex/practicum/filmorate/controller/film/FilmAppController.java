package ru.yandex.practicum.filmorate.controller.film;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import ru.yandex.practicum.filmorate.model.film.Film;

public interface FilmAppController<T> {

	/*
	 * создать или обновить фильм
	 */
	Film createOrUpdateFilm(@Valid @RequestBody T t);

	/*
	 * удалить фильм по id
	 */
	void deleteFilm(@PathVariable Long id);

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