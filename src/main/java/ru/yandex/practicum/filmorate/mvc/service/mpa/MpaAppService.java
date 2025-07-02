package ru.yandex.practicum.filmorate.mvc.service.mpa;

import java.util.List;

import ru.yandex.practicum.filmorate.model.mpa.Mpa;

public interface MpaAppService {

	/*
	 * получить mpa по id
	 */
	Mpa getMpa(int mpaId);

	/*
	 * получить список всех mpa
	 */
	List<Mpa> getAllMpa();

	/*
	 * проверить налдичие mpa в базе
	 */
	boolean isMpaExist(int mpaId);

}
