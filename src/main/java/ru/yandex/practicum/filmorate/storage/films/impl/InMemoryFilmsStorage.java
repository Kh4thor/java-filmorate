package ru.yandex.practicum.filmorate.storage.films.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.storage.films.FilmsAppStorage;

@Component
public class InMemoryFilmsStorage implements FilmsAppStorage<Film> {

	// хранилище фильмов
	private Map<Long, Film> filmsStorageMap = new HashMap<>();

	/*
	 * добавить фильм в хранилище
	 */
	@Override
	public Film addFilm(Film film) {
		filmsStorageMap.put(film.getId(), film);
		return film;
	}

	/*
	 * очистить хранилище
	 */
	@Override
	public void clear() {
		filmsStorageMap.clear();
	}

	/*
	 * проверить хранилище на наличие объекта
	 */
	@Override
	public boolean isFilmExist(Film film) {
		return filmsStorageMap.containsKey(film.getId());
	}

	/*
	 * получить фильм из хранилища
	 */
	@Override
	public Film getFilm(Long id) {
		return filmsStorageMap.get(id);
	}

	/*
	 * удалить фильм из хранилища
	 */
	@Override
	public Film removeFilm(Long id) {
		return filmsStorageMap.remove(id);
	}

	/*
	 * проверить хранилище на наличие объекта по id
	 */
	@Override
	public boolean isFilmExist(Long id) {
		return filmsStorageMap.containsKey(id);
	}

	/*
	 * вернуть спискок фильмов по рейтингу
	 */
	@Override
	public List<Film> getRatedFilms(List<Long> ratedFilmsIdList) {
		// получить по id-списку рейтинговых фильмов сами фильмы из хранилища фильмов
		return ratedFilmsIdList
				.stream()
				.map(id -> filmsStorageMap.get(id))
				.toList();
	}

	/*
	 * получить список всех фильмов
	 */
	@Override
	public List<Film> getAllFilms() {
		return filmsStorageMap
				.values()
				.stream()
				.toList();
	}
}
