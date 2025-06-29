package ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.ExceptionAppChecker;
import ru.yandex.practicum.filmorate.exceptions.filmExceptions.FilmAllreadyExistException;
import ru.yandex.practicum.filmorate.exceptions.filmExceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.friendExceptions.UsersAreAllreadyFriendsException;
import ru.yandex.practicum.filmorate.exceptions.friendExceptions.UsersAreNotFriendsException;
import ru.yandex.practicum.filmorate.exceptions.genreExceptions.GenreIsOutOfRangeException;
import ru.yandex.practicum.filmorate.exceptions.likeExceptions.IllegalNumberFilmsCountException;
import ru.yandex.practicum.filmorate.exceptions.likeExceptions.UserAllreadySetLikeToFilmException;
import ru.yandex.practicum.filmorate.exceptions.likeExceptions.UserDidntSetLikeToFilmException;
import ru.yandex.practicum.filmorate.exceptions.mpaExceptions.MpaIsOutOfRangeException;
import ru.yandex.practicum.filmorate.exceptions.userExceptions.UserAllreadyExistException;
import ru.yandex.practicum.filmorate.exceptions.userExceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.film.Genre;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.film.FilmAppStorage;
import ru.yandex.practicum.filmorate.storage.friend.FriendAppStorage;
import ru.yandex.practicum.filmorate.storage.like.LikeAppStorage;
import ru.yandex.practicum.filmorate.storage.user.UserAppStorage;

@Slf4j
@Component
public class ExceptionChecker implements ExceptionAppChecker {

	LikeAppStorage likeAppStorage;
	FriendAppStorage friendAppStorage;
	UserAppStorage<User> userAppStorage;
	FilmAppStorage<Film> filmAppStorage;

	ExceptionChecker(UserAppStorage<User> usersAppStorage, FriendAppStorage friendAppStorage,
			FilmAppStorage<Film> filmsAppStorage, LikeAppStorage likeAppStorage) {
		this.userAppStorage = usersAppStorage;
		this.friendAppStorage = friendAppStorage;
		this.filmAppStorage = filmsAppStorage;
		this.likeAppStorage = likeAppStorage;
	}

	/*
	 * проверка на ошибку - фильм уже существует
	 */
	@Override
	public void checkFilmIsExistException(Long filmId, String errorMessage) {
		if (filmAppStorage.isFilmExist(filmId)) {
			RuntimeException exception = new FilmAllreadyExistException(filmId, errorMessage);
			log.warn(errorMessage + " " + exception.getMessage());
			throw exception;
		}
	}

	/*
	 * проверка на ошибку - фильм не найден
	 */
	@Override
	public void checkFilmNotFoundException(Long filmId, String errorMessage) {
		if (!filmAppStorage.isFilmExist(filmId)) {
			RuntimeException exception = new FilmNotFoundException(filmId, errorMessage);
			log.warn(errorMessage + " " + exception.getMessage());
			throw exception;
		}
	}

	/*
	 * проверка на ошибку - пользователь не найден
	 */
	@Override
	public void checkUserNotFoundException(Long userId, String errorMessage) {
		if (!userAppStorage.isUserExist(userId)) {
			RuntimeException exception = new UserNotFoundException(userId, errorMessage);
			log.warn(errorMessage + " " + exception.getMessage());
			throw exception;
		}
	}

	/*
	 * проверка на ошибку - пользователь уже создан
	 */
	@Override
	public void checkUserIsExistException(Long userId, String errorMessage) {
		if (userAppStorage.isUserExist(userId)) {
			RuntimeException exception = new UserAllreadyExistException(userId, errorMessage);
			log.warn(errorMessage + " " + exception.getMessage());
			throw exception;
		}
	}

	/*
	 * проверка на ошибку - пользователи уже друзья
	 */
	@Override
	public void checkUsersAreFriendsException(Long userOneId, Long userTwoId, String errorMessage) {
		if (friendAppStorage.isUsersAssociatedAsFriends(userOneId, userTwoId)) {
			RuntimeException exception = new UsersAreAllreadyFriendsException(userOneId, userTwoId, errorMessage);
			log.warn(errorMessage + " " + exception.getMessage());
			throw exception;
		}
	}

	/*
	 * проверка на ошибку - пользователи не являются друзьями
	 */
	@Override
	public void checkUsersAreNotFriendsException(Long userOneId, Long userTwoId, String errorMessage) {
		if (!friendAppStorage.isUsersAssociatedAsFriends(userOneId, userTwoId)) {
			RuntimeException exception = new UsersAreNotFriendsException(userOneId, userTwoId, errorMessage);
			log.warn(errorMessage + " " + exception.getMessage());
			throw exception;
		}
	}

	/*
	 * проверка на ошибку - неверное число для вывода рейтинговых фильмов
	 */
	@Override
	public void checkIllegalNumberFilmsCountException(int countFilms, String errorMessage) {
		if (countFilms <= 0) {
			IllegalArgumentException exception = new IllegalNumberFilmsCountException(countFilms, errorMessage);
			log.warn(errorMessage + " " + exception.getMessage());
			throw exception;
		}
	}

	/*
	 * проверка на ошибку - пользователь уже ставил лайк фильму
	 */
	@Override
	public void checkUserAllreadySetLikeToFilmException(Long filmId, Long userId, String errorMessage) {
		if (likeAppStorage.isUserSetLike(filmId, userId)) {
			RuntimeException exception = new UserAllreadySetLikeToFilmException(filmId, userId, errorMessage);
			log.warn(errorMessage + " " + exception.getMessage());
			throw exception;
		}
	}

	/*
	 * проверка на ошибку - пользователь не ставил лайк фильму
	 */
	@Override
	public void checkUserDidntSetLikeToFilmException(Long filmId, Long userId, String errorMessage) {
		if (!likeAppStorage.isUserSetLike(filmId, userId)) {
			RuntimeException exception = new UserDidntSetLikeToFilmException(filmId, userId, errorMessage);
			log.warn(errorMessage + " " + exception.getMessage());
			throw exception;
		}
	}

	@Override
	public void checkMpaRangeValueException(Integer mpaId, String errorMessage) {
		if (mpaId < 1 || mpaId > 5) {
			RuntimeException exception = new MpaIsOutOfRangeException(mpaId, errorMessage);
			log.warn(errorMessage + " " + exception.getMessage());
			throw exception;
		}
	}

	@Override
	public void checkGenreIsOutOfRangeException(List<Genre> genres, String errorMessage) {
		for (int i = 0; i < genres.size(); i++) {
			int genreId = genres.get(i).getId();
			if (genreId < 1 || genreId > 6) {
				RuntimeException exception = new GenreIsOutOfRangeException(genreId, errorMessage);
				log.warn(errorMessage + " " + exception.getMessage());
				throw exception;
			}
		}
	}
}
