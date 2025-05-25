package ru.yandex.practicum.filmorate.exceptions.exceptionsChecker;

import ru.yandex.practicum.filmorate.exceptions.userExceptions.UserNotFoundException;

public interface ExceptionsAppChecker {

	/*
	 * проверка на ошибку - фильм уже существует
	 */
	void checkFilmAllreadyExist(long filmId, String error);

	/*
	 * проверка на ошибку - фильм не найден
	 */
	void checkFilmNotFoundException(long filmId, String error);

	/*
	 * проверка на ошибку - пользователь не найден
	 */
	void checkUserNotFoundException(long userId, String error) throws UserNotFoundException;

	/*
	 * проверка на ошибку - пользователь не найден
	 */
	void checkUserAllreadyExist(long userId, String error) throws UserNotFoundException;

}