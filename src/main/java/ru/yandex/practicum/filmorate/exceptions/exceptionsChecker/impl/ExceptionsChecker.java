package ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.impl;

import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.ExceptionsAppChecker;
import ru.yandex.practicum.filmorate.exceptions.filmExceptions.FilmAllreadyExistException;
import ru.yandex.practicum.filmorate.exceptions.filmExceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.userExceptions.UserAllreadyExistException;
import ru.yandex.practicum.filmorate.exceptions.userExceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.films.FilmsAppStorage;
import ru.yandex.practicum.filmorate.storage.friends.FriendsAppStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikesAppStorage;
import ru.yandex.practicum.filmorate.storage.users.UsersAppStorage;

public class ExceptionsChecker implements ExceptionsAppChecker {

	LikesAppStorage likesAppStorage;
	UsersAppStorage<User> usersAppStorage;
	FriendsAppStorage<User> friendsAppStorage;
	FilmsAppStorage<Film> filmsAppStorage;

	ExceptionsChecker(UsersAppStorage<User> usersAppStorage, FriendsAppStorage<User> friendsAppStorage,
			FilmsAppStorage<Film> filmsAppStorage) {
		this.usersAppStorage = usersAppStorage;
		this.friendsAppStorage = friendsAppStorage;
		this.filmsAppStorage = filmsAppStorage;

	}

	/*
	 * проверка на ошибку - фильм уже существует
	 */
	@Override
	public void checkFilmAllreadyExist(long filmId, String error) {
		if (likesAppStorage.isFilmInLikesAppStorageExist(filmId)) {
			throw new FilmAllreadyExistException(filmId, error);
		}
	}

	/*
	 * проверка на ошибку - фильм не найден
	 */
	@Override
	public void checkFilmNotFoundException(long filmId, String error) {
		if (!likesAppStorage.isFilmInLikesAppStorageExist(filmId)) {
			throw new FilmNotFoundException(filmId, error);
		}
	}

	/*
	 * проверка на ошибку - пользователь не найден
	 */
	@Override
	public void checkUserNotFoundException(long userId, String error) throws UserNotFoundException {
		if (!usersAppStorage.isEntityExist(userId)) {
			throw new UserNotFoundException(userId, error);
		}
	}

	/*
	 * проверка на ошибку - пользователь не найден
	 */
	@Override
	public void checkUserAllreadyExist(long userId, String error) throws UserNotFoundException {
		if (!usersAppStorage.isEntityExist(userId)) {
			throw new UserAllreadyExistException(userId, error);
		}
	}

}
