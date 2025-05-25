package ru.yandex.practicum.filmorate.service.likes;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.impl.ExceptionsChecker;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.storage.films.FilmsAppStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikesAppStorage;

@Component
public class LikeService {

	private final FilmsAppStorage<Film> filmsAppStorage;

	LikesAppStorage likesAppStorage;
	ExceptionsChecker exceptionsChecker;

	public LikeService(LikesAppStorage likesAppStorage, ExceptionsChecker exceptionsChecker,
			FilmsAppStorage<Film> filmsAppStorage) {
		this.likesAppStorage = likesAppStorage;
		this.exceptionsChecker = exceptionsChecker;
		this.filmsAppStorage = filmsAppStorage;
	}

	/*
	 * поставить лайк фильму
	 */
	public void setLike(long filmId, long userId) {
		String error = "Невозможно поставить лайк фильму";
		exceptionsChecker.checkFilmNotFoundException(filmId, error);
		exceptionsChecker.checkUserNotFoundException(userId, error);
		likesAppStorage.setLike(filmId, userId);
	}

	/*
	 * удалить лайк
	 */
	public void removeLike(long filmId, long userId) {
		String error = "Невозможно убрать лайк у фильма";
		exceptionsChecker.checkFilmNotFoundException(filmId, error);
		exceptionsChecker.checkUserNotFoundException(userId, error);
		likesAppStorage.removeLike(filmId, userId);
	}

	/*
	 * вернуть список из первых count-фильмов по количеству лайков
	 */
	public List<Film> getRatedFilms(int count) {
		List<Long> ratedFilmsIdList = likesAppStorage.getIdListOfFilmsIdByRate(count);
		Map<Long, Film> filmsRepository = filmsAppStorage.getRepository();
		return ratedFilmsIdList.stream().map(id -> filmsRepository.get(id)).toList();
	}
}
