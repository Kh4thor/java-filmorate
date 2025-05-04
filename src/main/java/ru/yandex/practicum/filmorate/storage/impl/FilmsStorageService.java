package ru.yandex.practicum.filmorate.storage.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.storage.StorageService;

@Service
public class FilmsStorageService implements StorageService<Film> {

	// хранилище фильмов
	private Map<Long, Film> filmStorage = new HashMap<>();

	/*
	 * добавить фильм в хранилище
	 */
	@Override
	public Film add(Film film) {
		filmStorage.put(film.getId(), film);
		return film;
	}

	/*
	 * очистить хранилище
	 */
	@Override
	public void clear() {
		filmStorage.clear();
	}

	/*
	 * проверить хранилище на наличие ключа id-фильма
	 */
	@Override
	public boolean containsKey(Long id) {
		return filmStorage.containsKey(id);
	}

	/*
	 * получить фильм из хранилища
	 */
	@Override
	public Film get(Long id) {
		return filmStorage.get(id);
	}

	/*
	 * получить хранилище фильмов
	 */
	@Override
	public Map<Long, Film> getRepository() {
		return new HashMap<>(filmStorage);
	}

	/*
	 * удалить фильм из хранилища
	 */
	@Override
	public Film remove(Long id) {
		return filmStorage.remove(id);
	}
}
