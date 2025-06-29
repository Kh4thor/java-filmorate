package ru.yandex.practicum.filmorate.service.mpa;

import java.util.List;

public interface MpaAppService<T> {

	/*
	 * получить mpa по id
	 */
	T getMpa(int mpaId);

	/*
	 * получить список всех mpa
	 */
	List<T> getAllMpa();

}
