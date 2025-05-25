package ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.impl;

import org.springframework.stereotype.Component;

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

@Component
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

	/*
	 * проверка на ошибку - пользователи уже друзья
	 */
	@Override
	public void checkUsersAreAllredayFriendsException(long userOneId, long userTwoId, String error) {
		if (friendsAppStorage.isEntitiesAssociated(userOneId, userTwoId)) {
			throw new UsersAreAllreadyFriendsException(userOneId, userTwoId, error);
		}
	}

	/*
	 * проверка на ошибку - пользователи не являются друзьями
	 */
	@Override
	public void checkUsersAreNotFriendsException(long userOneId, long userTwoId, String error) {
		if (!friendsAppStorage.isEntitiesAssociated(userOneId, userTwoId)) {
			throw new UsersAreNotFriendsException(userOneId, userTwoId, error);
		}
	}

}
