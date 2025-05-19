package ru.yandex.practicum.filmorate.service.film.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.service.film.FilmAppService;
import ru.yandex.practicum.filmorate.storage.film.FilmAppStorage;

@Slf4j
@Service
public class FilmService implements FilmAppService<Film> {

	private long id = 0;

	FilmAppStorage<Film> filmAppStorage;

	public FilmService(FilmAppStorage<Film> filmAppStorage) {
		this.filmAppStorage = filmAppStorage;
	}

	/*
	 * создать или обновить фильм
	 */
	@Override
	public Film createOrUpdate(Film film) {
		if (film.getId() == null || film.getId() == 0) {
			log.info("Начато создание фильма. Получен объект {}", film);
			Film createdFilm = create(film);
			log.info("Фильм {} успешно добавлен", createdFilm);
			return createdFilm;
		} else if (filmAppStorage.isEntityExist(film)) {
			log.info("Начато обновление фильма. Получен объект {}", film);
			Film updatedFilm = update(film);
			log.info("Фильм {} успешно обновлен", updatedFilm);
			return updatedFilm;
		} else {
			return null;
		}
	}

	/*
	 * удалить фильм по id
	 */
	@Override
	public Film delete(long id) {
		return filmAppStorage.remove(id);
	}

	/*
	 * удалить все фильмы
	 */
	@Override
	public void deleteAll() {
		filmAppStorage.clear();
	}

	/*
	 * получить фильм по id
	 */
	@Override
	public Film get(long id) {
		return filmAppStorage.get(id);
	}

	/*
	 * получить список всех фильмов
	 */
	@Override
	public List<Film> getAll() {
		return filmAppStorage.getRepository().values().stream().toList();
	}

	/*
	 * генератор id для нового фильма
	 */
	private long generateId() {
		return ++id;
	}

	/*
	 * создать фильм
	 */
	private Film create(Film film) {
		film.setId(generateId());
		return filmAppStorage.add(film);
	}

	/*
	 * обновить фильм
	 */
	private Film update(Film film) {
		return filmAppStorage.add(film);
	}
}