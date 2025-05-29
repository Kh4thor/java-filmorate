package ru.yandex.practicum.filmorate.service.films.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.ExceptionsAppChecker;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.service.films.FilmsAppService;
import ru.yandex.practicum.filmorate.storage.films.FilmsAppStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikesAppStorage;

@Slf4j
@Service
public class FilmsService implements FilmsAppService<Film> {

	private Long id = 0L;

	private final FilmsAppStorage<Film> filmsAppStorage;
	private final LikesAppStorage likesAppStorage;
	private final ExceptionsAppChecker exceptionsAppChecker;

	public FilmsService(FilmsAppStorage<Film> filmsAppStorage, LikesAppStorage likesAppStorage,
			ExceptionsAppChecker exceptionsChecker) {
		this.filmsAppStorage = filmsAppStorage;
		this.likesAppStorage = likesAppStorage;
		this.exceptionsAppChecker = exceptionsChecker;
	}

	/*
	 * создать или обновить фильм
	 */
	@Override
	public Film createOrUpdateFilm(Film film) {
		if (film.getId() == null || film.getId() == 0) {
			log.info("Начато создание фильма. Получен объект {}", film);
			Film createdFilm = create(film);
			log.info("Фильм {} успешно добавлен", createdFilm);
			return createdFilm;
		} else {
			log.info("Начато обновление фильма. Получен объект {}", film);
			Film updatedFilm = update(film);
			log.info("Фильм {} успешно обновлен", updatedFilm);
			return updatedFilm;
		}
	}

	/*
	 * удалить фильм по id
	 */
	@Override
	public Film deleteFilm(Long filmId) {
		String errorMessage = "Невозможно удалить фильм.";
		exceptionsAppChecker.checkFilmNotFoundException(filmId, errorMessage);
		likesAppStorage.deleteFilm(filmId);
		log.info("Фильм с id=" + filmId + " удален");
		return filmsAppStorage.removeFilm(filmId);
	}

	/*
	 * удалить все фильмы
	 */
	@Override
	public void deleteAllFilms() {
		filmsAppStorage.clear();
	}

	/*
	 * получить фильм по id
	 */
	@Override
	public Film getFilm(Long filmId) {
		String errorMessage = "Невозможно получить фильм";
		exceptionsAppChecker.checkUserNotFoundException(filmId, errorMessage);
		exceptionsAppChecker.checkFilmNotFoundException(filmId, null);
		return filmsAppStorage.getFilm(filmId);
	}

	/*
	 * получить список всех фильмов
	 */
	@Override
	public List<Film> getAllFilms() {
		return filmsAppStorage.getAllFilms();
	}

	/*
	 * генератор id для нового фильма
	 */
	private Long generateId() {
		return ++id;
	}

	/*
	 * создать фильм
	 */
	private Film create(Film film) {
		String errorMessage = "Невозможно создать фильм";
		film.setId(generateId());
		exceptionsAppChecker.checkFilmIsExistException(film.getId(), errorMessage);
		likesAppStorage.addFilm(film);
		return filmsAppStorage.addFilm(film);
	}

	/*
	 * обновить фильм
	 */
	private Film update(Film film) {
		String errorMessage = "Невозможно обновить фильм";
		exceptionsAppChecker.checkFilmNotFoundException(film.getId(), errorMessage);
		return filmsAppStorage.addFilm(film);
	}
}