package ru.yandex.practicum.filmorate.storage.mpa;

import java.util.List;

import ru.yandex.practicum.filmorate.model.film.Mpa;

public interface MpaAppStorage {

	/*
	 * получить mpa по id
	 */
	Mpa getMpa(int mpaId);

	/*
	 * получить список всех mpa
	 */
	List<Mpa> getAllMpa();
}
