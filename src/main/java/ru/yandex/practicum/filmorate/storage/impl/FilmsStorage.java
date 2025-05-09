package ru.yandex.practicum.filmorate.storage.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.storage.AppStorage;

@Service
public class FilmsStorage implements AppStorage<Film> {

	// хранилище фильмов
	private Map<Long, Film> appStorage = new HashMap<>();

	/*
	 * добавить фильм в хранилище
	 */
	@Override
	public Film add(Film film) {
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
	 * проверить хранилище на наличие ключа id-фильма
	 */
	@Override
	public boolean isEntityExist(Film film) {
		return appStorage.containsKey(film.getId());
	}

	/*
	 * получить фильм из хранилища
	 */
	@Override
	public Film get(Long id) {
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
	public Film remove(Long id) {
		return appStorage.remove(id);
	}
}
