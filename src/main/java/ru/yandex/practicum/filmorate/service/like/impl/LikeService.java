package ru.yandex.practicum.filmorate.service.like.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.impl.ExceptionChecker;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.service.like.LikeAppService;
import ru.yandex.practicum.filmorate.storage.film.FilmAppStorage;
import ru.yandex.practicum.filmorate.storage.like.LikeAppStorage;

@Slf4j
@Component
public class LikeService implements LikeAppService {

	private final LikeAppStorage likeAppStorage;
	private final FilmAppStorage<Film> filmAppStorage;
	private final ExceptionChecker exceptionsChecker;

	public LikeService(LikeAppStorage likeAppStorage, ExceptionChecker exceptionsChecker,
			FilmAppStorage<Film> filmsAppStorage) {
		this.likeAppStorage = likeAppStorage;
		this.exceptionsChecker = exceptionsChecker;
		this.filmAppStorage = filmsAppStorage;
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
		likeAppStorage.setLike(filmId, userId);
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
		likeAppStorage.removeLike(filmId, userId);
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
		List<Long> ratedFilmsIdList = likeAppStorage.getIdListOfFilmsIdByRate(count);
		// список рейтиноговых фильмов из хранилища фильмов по id-
		List<Film> ratedFilmsList = filmAppStorage.getRatedFilms(ratedFilmsIdList);
		if (ratedFilmsList.isEmpty()) {
			log.info("Список рейтинговых фильмов пуст");
		} else {
			log.info("Выведен список из " + ratedFilmsList.size() + " рейтинговых фильмов");
		}
		return ratedFilmsList;
	}
}
