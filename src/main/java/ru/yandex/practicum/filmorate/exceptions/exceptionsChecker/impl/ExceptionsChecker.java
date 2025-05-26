package ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.impl;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.ExceptionsAppChecker;
import ru.yandex.practicum.filmorate.exceptions.filmExceptions.FilmAllreadyExistException;
import ru.yandex.practicum.filmorate.exceptions.filmExceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.userExceptions.UserAllreadyExistException;
import ru.yandex.practicum.filmorate.exceptions.userExceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.userExceptions.UsersAreAllreadyFriendsException;
import ru.yandex.practicum.filmorate.exceptions.userExceptions.UsersAreNotFriendsException;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.films.FilmsAppStorage;
import ru.yandex.practicum.filmorate.storage.friends.FriendsAppStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikesAppStorage;
import ru.yandex.practicum.filmorate.storage.users.UsersAppStorage;

@Slf4j
@Component
public class ExceptionsChecker implements ExceptionsAppChecker {

	LikesAppStorage likesAppStorage;
	FriendsAppStorage friendsAppStorage;
	UsersAppStorage<User> usersAppStorage;
	FilmsAppStorage<Film> filmsAppStorage;

	ExceptionsChecker(UsersAppStorage<User> usersAppStorage, FriendsAppStorage friendsAppStorage,
			FilmsAppStorage<Film> filmsAppStorage, LikesAppStorage likesAppStorage) {
		this.usersAppStorage = usersAppStorage;
		this.friendsAppStorage = friendsAppStorage;
		this.filmsAppStorage = filmsAppStorage;
		this.likesAppStorage = likesAppStorage;
	}

	/*
	 * проверка на ошибку - фильм уже существует
	 */
	@Override
	public void checkFilmIsExistException(Long filmId, String errorMessage) {
		if (filmsAppStorage.isFilmExist(filmId)) {
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
		if (!filmsAppStorage.isFilmExist(filmId)) {
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
		if (usersAppStorage.isUserExist(userId) == false) {
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
		if (usersAppStorage.isUserExist(userId) == false) {
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
		if (friendsAppStorage.isUsersAssociatedAsFriends(userOneId, userTwoId)) {
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
		if (friendsAppStorage.isUsersAssociatedAsFriends(userOneId, userTwoId) == false) {
			RuntimeException exception = new UsersAreNotFriendsException(userOneId, userTwoId, errorMessage);
			log.warn(errorMessage + " " + exception.getMessage());
			throw exception;
		}
	}

}
