package ru.yandex.practicum.filmorate.service.likes.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.impl.ExceptionsChecker;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.service.likes.LikesAppService;
import ru.yandex.practicum.filmorate.storage.films.FilmsAppStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikesAppStorage;

@Slf4j
@Component
public class LikesService implements LikesAppService {

	private final LikesAppStorage likesAppStorage;
	private final FilmsAppStorage<Film> filmsAppStorage;
	private final ExceptionsChecker exceptionsChecker;

	public LikesService(LikesAppStorage likesAppStorage, ExceptionsChecker exceptionsChecker,
			FilmsAppStorage<Film> filmsAppStorage) {
		this.likesAppStorage = likesAppStorage;
		this.exceptionsChecker = exceptionsChecker;
		this.filmsAppStorage = filmsAppStorage;
	}

	/*
	 * поставить лайк фильму
	 */
	@Override
	public void setLike(Long filmId, Long userId) {
		String errorMessage = "Невозможно поставить лайк фильму";
		exceptionsChecker.checkFilmNotFoundException(filmId, errorMessage);
		exceptionsChecker.checkUserNotFoundException(userId, errorMessage);
		exceptionsChecker.checkUserAllreadySetLikeToFilmException(filmId, userId, errorMessage);
		likesAppStorage.setLike(filmId, userId);
		log.info("Пользователь id=" + userId + " поставил лайк фильму id=" + filmId);
	}

	/*
	 * удалить лайк
	 */
	@Override
	public void removeLike(Long filmId, Long userId) {
		String errorMessage = "Невозможно убрать лайк у фильма";
		exceptionsChecker.checkFilmNotFoundException(filmId, errorMessage);
		exceptionsChecker.checkUserNotFoundException(userId, errorMessage);
		exceptionsChecker.checkUserDidntSetLikeToFilmException(filmId, userId, errorMessage);
		likesAppStorage.removeLike(filmId, userId);
		log.info("Пользователь id=" + userId + " удалил лайк у фильма id=" + filmId);
	}

	/*
	 * вернуть список из первых count-фильмов по количеству лайков
	 */
	@Override
	public List<Film> getRatedFilms(Integer count) {
		String errorMessage = "Невозможно получить список рейтиноговых фильмов";
		exceptionsChecker.checkIllegalNumberFilmsCountException(count, errorMessage);

		// id-cписок рейтинговых фильмов из хранилища лайков, ограниченный по длине count
		List<Long> ratedFilmsIdList = likesAppStorage.getIdListOfFilmsIdByRate(count);
		// список рейтиноговых фильмов из хранилища фильмов по id-
		List<Film> ratedFilmsList = filmsAppStorage.getRatedFilms(ratedFilmsIdList);
		if (ratedFilmsList.isEmpty()) {
			log.info("Список рейтинговых фильмов пуст");
		} else {
			log.info("Выведен список из " + ratedFilmsList.size() + " рейтинговых фильмов");
		}
		return ratedFilmsList;
	}
}
