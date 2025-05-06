package ru.yandex.practicum.filmorate.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.service.AppService;
import ru.yandex.practicum.filmorate.storage.AppStorage;

@Slf4j
@Service
public class FilmService implements AppService<Film> {

	private long id = 0;

	AppStorage<Film> appStorage;

	public FilmService(AppStorage<Film> appStorage) {
		this.appStorage = appStorage;
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
		} else if (appStorage.containsKey(film.getId())) {
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
		return appStorage.remove(id);
	}

	/*
	 * удалить все фильмы
	 */
	@Override
	public void deleteAll() {
		appStorage.clear();
	}

	/*
	 * получить фильм по id
	 */
	@Override
	public Film get(long id) {
		return appStorage.get(id);
	}

	/*
	 * получить список всех фильмов
	 */
	@Override
	public List<Film> getAll() {
		return appStorage
				.getRepository()
				.values()
				.stream()
				.toList();
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
		return appStorage.add(film);
	}

	/*
	 * обновить фильм
	 */
	private Film update(Film film) {
		return appStorage.add(film);
	}
}