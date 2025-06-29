package ru.yandex.practicum.filmorate.controller.mpa;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import ru.yandex.practicum.filmorate.model.film.Mpa;

public interface MpaAppController<T> {

	/*
	 * получить mpa по id
	 */
	Mpa getMpa(@PathVariable int mpaId);

	/*
	 * получить список всех mpa
	 */
	List<T> getAllMpa();
}
