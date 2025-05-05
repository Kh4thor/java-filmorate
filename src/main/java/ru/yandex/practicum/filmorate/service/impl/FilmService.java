package ru.yandex.practicum.filmorate.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.AppService;
import ru.yandex.practicum.filmorate.storage.StorageService;

@Slf4j
@Service
public class FilmService implements AppService<Film> {

	private long id = 0;

	StorageService<Film> storageService;

	public FilmService(StorageService<Film> storageService) {
		this.storageService = storageService;
	}

	/*
	 * создать или обновить фильм
	 */
	@Override
	public Film createOrUpdate(Film film) {
		if (film.getId() == null || film.getId() == 0) {
			logCreation(film);
			return create(film);
		} else if (storageService.containsKey(film.getId())) {
			logUpdating(film);
			return update(film);
		} else {
			return null;
		}
	}

	/*
	 * удалить фильм по id
	 */
	@Override
	public Film delete(long id) {
		return storageService.remove(id);
	}

	/*
	 * удалить все фильмы
	 */
	@Override
	public void deleteAll() {
		storageService.clear();
	}

	/*
	 * получить фильм по id
	 */
	@Override
	public Film get(long id) {
		return storageService.get(id);
	}

	/*
	 * получить список всех фильмов
	 */
	@Override
	public List<Film> getAll() {
		return storageService.getRepository().values().stream().toList();
	}

	/*
	 * генератор id для нового фильма
	 */
	private long generateId() {
		return ++id;
	}

	/*
	 * если сгенерированный id- нового фильма совпал с id-добавленного фильма
	 */
	private void checkId(Film film) {
		while (storageService.containsKey(film.getId())) {
			film.setId(generateId());
		}
	}

	/*
	 * создать фильм
	 */
	private Film create(Film film) {
		if (film.getId() == null || film.getId() == 0) {
			film.setId(generateId());
			checkId(film);
		}
		return storageService.add(film);
	}

	/*
	 * обновить фильм
	 */
	private Film update(Film film) {
		return storageService.add(film);
	}

	private void logCreation(Film film) {
		log.info("Начато создание фильма. Получен объект {}", film);
		if (film.getId() == null || film.getId() == 0) {
			log.info("Фильм {} успешно добавлен");
		} else {
			log.warn("Неверно заданы параметры фильма {}", film);
		}
	}

	private void logUpdating(Film film) {
		log.info("Начато обновление фильма. Получен объект {}", film);
		if (storageService.containsKey(film.getId())) {
			log.info("Фильм {} успешно обновлен", film);
		} else {
			log.warn("Неверно заданы параметры фильма {}", film);
		}
	}
}