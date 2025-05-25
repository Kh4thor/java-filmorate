package ru.yandex.practicum.filmorate.service.films.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.ExceptionsAppChecker;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.films.FilmsAppService;
import ru.yandex.practicum.filmorate.storage.films.FilmsAppStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikesAppStorage;
import ru.yandex.practicum.filmorate.storage.users.UsersAppStorage;

@Slf4j
@Service
public class FilmsService implements FilmsAppService<Film> {

	private long id = 0;

	private final FilmsAppStorage<Film> filmsAppStorage;
	private final LikesAppStorage likesAppStorage;
	private final ExceptionsAppChecker exceptionsAppChecker;

	public FilmsService(FilmsAppStorage<Film> filmsAppStorage, LikesAppStorage likesAppStorage,
			UsersAppStorage<User> usersAppStorage, ExceptionsAppChecker exceptionsChecker) {
		this.filmsAppStorage = filmsAppStorage;
		this.likesAppStorage = likesAppStorage;
		this.exceptionsAppChecker = exceptionsChecker;
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
		} else if (filmsAppStorage.isFilmExist(film)) {
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
	public Film delete(long filmId) {
		String error = "Невозможно удалить фильм из хранилища лайков.";
		exceptionsAppChecker.checkFilmNotFoundException(filmId, error);
		likesAppStorage.deleteFilm(filmId);
		log.info("Фильм с id=" + filmId + " удален");
		return filmsAppStorage.removeFilm(filmId);

	}

	/*
	 * удалить все фильмы
	 */
	@Override
	public void deleteAll() {
		filmsAppStorage.clear();
	}

	/*
	 * получить фильм по id
	 */
	@Override
	public Film get(long id) {
		return filmsAppStorage.getFilm(id);
	}

	/*
	 * получить список всех фильмов
	 */
	@Override
	public List<Film> getAll() {
		return filmsAppStorage.getRepository().values().stream().toList();
	}

	/*
	 * поставить лайк фильму
	 */
	public void setLikeToFilm(long userId, long filmId) {
		String error = "Невозможно поставить лайк фильму";
		exceptionsAppChecker.checkFilmNotFoundException(filmId, error);
		exceptionsAppChecker.checkUserNotFoundException(userId, error);
		likesAppStorage.setLike(filmId, userId);
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
		String error = "Невозможно создать фильм";
		film.setId(generateId());
		exceptionsAppChecker.checkFilmAllreadyExist(film.getId(), error);
		likesAppStorage.addFilm(film.getId());
		return filmsAppStorage.addFilm(film);
	}

	/*
	 * обновить фильм
	 */
	private Film update(Film film) {
		String error = "Невозможно обновить фильм";
		exceptionsAppChecker.checkFilmNotFoundException(film.getId(), error);
		likesAppStorage.addFilm(film.getId());
		return filmsAppStorage.addFilm(film);
	}

}