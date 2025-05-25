package ru.yandex.practicum.filmorate.exceptions.exceptionsChecker;

public interface ExceptionsAppChecker {

	/*
	 * проверка на ошибку - фильм уже существует
	 */
	void checkFilmIsExistException(long filmId, String error);

	/*
	 * проверка на ошибку - фильм не найден
	 */
	void checkFilmNotFoundException(long filmId, String error);

	/*
	 * проверка на ошибку - пользователь не найден
	 */
	void checkUserNotFoundException(long userId, String error);

	/*
	 * проверка на ошибку - пользователь не найден
	 */
	void checkUserIsExistException(long userId, String error);

	/*
	 * проверка на ошибку - пользователи уже друзья
	 */
	void checkUsersAreFriendsException(long userOneId, long userTwoId, String error);

	/*
	 * проверка на ошибку - пользователи не являются друзьями
	 */
	void checkUsersAreNotFriendsException(long userOneId, long userTwoId, String error);

}