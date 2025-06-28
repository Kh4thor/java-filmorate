package ru.yandex.practicum.filmorate.storage.film;

import java.util.List;

public interface FilmAppStorage<T> {

	/*
	 * добавить фильм в хранилище
	 */
	T addFilm(T film);

	/*
	 * обновить фильм в хранилище
	 */
	T updateFilm(T film);

	/*
	 * очистить хранилище
	 */
	void clear();

	/*
	 * проверить хранилище на наличие ключа-id фильма
	 */
	boolean isFilmExist(Long filmId);

	/*
	 * получить фильм из хранилища
	 */
	T getFilm(Long id);

	/*
	 * удалить фильм из хранилища
	 */
	T removeFilm(Long filmId);

	/*
	 * вернуть спискок фильмов по рейтингу
	 */
	List<T> getRatedFilms(List<Long> ratedFilmsIdList);

	/*
	 * получить список всех фильмов
	 */
	List<T> getAllFilms();
}
