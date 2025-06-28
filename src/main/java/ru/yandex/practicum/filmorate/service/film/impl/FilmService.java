package ru.yandex.practicum.filmorate.service.film.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.ExceptionAppChecker;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.service.film.FilmAppService;
import ru.yandex.practicum.filmorate.storage.film.FilmAppStorage;
import ru.yandex.practicum.filmorate.storage.like.LikeAppStorage;

@Slf4j
@Service
public class FilmService implements FilmAppService<Film> {

	private Long id = 0L;

	private final FilmAppStorage<Film> filmAppStorage;
	private final LikeAppStorage likeAppStorage;
	private final ExceptionAppChecker exceptionsAppChecker;

	public FilmService(FilmAppStorage<Film> filmsAppStorage, LikeAppStorage likeAppStorage,
			ExceptionAppChecker exceptionsChecker) {
		this.filmAppStorage = filmsAppStorage;
		this.likeAppStorage = likeAppStorage;
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
		likeAppStorage.deleteFilm(filmId);
		log.info("Фильм с id=" + filmId + " удален");
		return filmAppStorage.removeFilm(filmId);
	}

	/*
	 * удалить все фильмы
	 */
	@Override
	public void deleteAllFilms() {
		filmAppStorage.clear();
	}

	/*
	 * получить фильм по id
	 */
	@Override
	public Film getFilm(Long filmId) {
		String errorMessage = "Невозможно получить фильм";
		exceptionsAppChecker.checkUserNotFoundException(filmId, errorMessage);
		exceptionsAppChecker.checkFilmNotFoundException(filmId, null);
		return filmAppStorage.getFilm(filmId);
	}

	/*
	 * получить список всех фильмов
	 */
	@Override
	public List<Film> getAllFilms() {
		return filmAppStorage.getAllFilms();
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
		likeAppStorage.addFilm(film);
		return filmAppStorage.addFilm(film);
	}

	/*
	 * обновить фильм
	 */
	private Film update(Film film) {
		String errorMessage = "Невозможно обновить фильм";
		exceptionsAppChecker.checkFilmNotFoundException(film.getId(), errorMessage);
		return filmAppStorage.updateFilm(film);
	}
}