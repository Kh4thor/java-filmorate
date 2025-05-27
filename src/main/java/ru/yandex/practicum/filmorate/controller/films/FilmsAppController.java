package ru.yandex.practicum.filmorate.controller.films;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import ru.yandex.practicum.filmorate.model.film.Film;

public interface FilmsAppController<T> {

	/*
	 * создать или обновить объект
	 */
	Film createOrUpdateFilm(@Valid @RequestBody T t);

	/*
	 * удалить объект по id
	 */
	void deleteFilm(@PathVariable Long id);

	/*
	 * удалить все объекты
	 */
	void deleteAllFilms();

	/*
	 * получить объект по id
	 */
	ResponseEntity<T> getFilm(@PathVariable Long id);

	/*
	 * получить список всех объектов
	 */
	List<T> getAllFilms();
}