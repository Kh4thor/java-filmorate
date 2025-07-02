package ru.yandex.practicum.filmorate.mvc.storage.mpa;

import java.util.List;

import ru.yandex.practicum.filmorate.model.mpa.Mpa;

public interface MpaAppStorage {

	/*
	 * получить mpa по id
	 */
	Mpa getMpa(int mpaId);

	/*
	 * получить список всех mpa
	 */
	List<Mpa> getAllMpa();

	/*
	 * проверить наличие mpa в базе
	 */
	boolean isMpaExist(int mpaId);
}
