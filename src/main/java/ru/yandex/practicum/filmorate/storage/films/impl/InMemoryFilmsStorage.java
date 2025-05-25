package ru.yandex.practicum.filmorate.storage.films.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.storage.films.FilmsAppStorage;

@Component
public class InMemoryFilmsStorage implements FilmsAppStorage<Film> {

	// хранилище фильмов
	private Map<Long, Film> appStorage = new HashMap<>();

	/*
	 * добавить фильм в хранилище
	 */
	@Override
	public Film addFilm(Film film) {
		appStorage.put(film.getId(), film);
		return film;
	}

	/*
	 * очистить хранилище
	 */
	@Override
	public void clear() {
		appStorage.clear();
	}

	/*
	 * проверить хранилище на наличие объекта
	 */
	@Override
	public boolean isFilmExist(Film film) {
		return appStorage.containsKey(film.getId());
	}

	/*
	 * получить фильм из хранилища
	 */
	@Override
	public Film getFilm(Long id) {
		return appStorage.get(id);
	}

	/*
	 * получить хранилище фильмов
	 */
	@Override
	public Map<Long, Film> getRepository() {
		return new HashMap<>(appStorage);
	}

	/*
	 * удалить фильм из хранилища
	 */
	@Override
	public Film removeFilm(Long id) {
		return appStorage.remove(id);
	}

	/*
	 * проверить хранилище на наличие объекта по id
	 */
	@Override
	public boolean isFilmExist(long id) {
		return appStorage.containsKey(id);
	}
}
