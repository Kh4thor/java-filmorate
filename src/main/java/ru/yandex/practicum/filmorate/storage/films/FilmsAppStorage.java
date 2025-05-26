package ru.yandex.practicum.filmorate.storage.films;

import java.util.Map;

public interface FilmsAppStorage<T> {

	/*
	 * добавить объект в хранилище
	 */
	T addFilm(T t);

	/*
	 * очистить хранилище
	 */
	void clear();

	/*
	 * проверить хранилище на наличие объекта
	 */
	boolean isFilmExist(T t);

	/*
	 * проверить хранилище на наличие ключа-id объекта
	 */
	boolean isFilmExist(Long id);

	/*
	 * получить объект из хранилища
	 */
	T getFilm(Long id);

	/*
	 * получить хранилище
	 */
	Map<Long, T> getRepository();

	/*
	 * удалить объект из хранилища
	 */
	T removeFilm(Long id);
}
