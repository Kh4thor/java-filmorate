package ru.yandex.practicum.filmorate.service.film;

import java.util.List;

public interface FilmAppService<T> {

	/*
	 * создать или обновить объект
	 */
	T createOrUpdateFilm(T t);

	/*
	 * удалить объект по id
	 */
	T deleteFilm(Long id);

	/*
	 * удалить все объекты
	 */
	void deleteAllFilms();

	/*
	 * получить объект по id
	 */
	T getFilm(Long id);

	/*
	 * получить список всех объектов
	 */
	List<T> getAllFilms();
}