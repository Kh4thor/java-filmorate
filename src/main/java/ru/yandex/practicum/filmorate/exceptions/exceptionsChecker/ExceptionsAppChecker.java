package ru.yandex.practicum.filmorate.exceptions.exceptionsChecker;

public interface ExceptionsAppChecker {

	/*
	 * проверка на ошибку - фильм уже существует
	 */
	void checkFilmIsExistException(Long filmId, String errorMessage);

	/*
	 * проверка на ошибку - фильм не найден
	 */
	void checkFilmNotFoundException(Long filmId, String errorMessage);

	/*
	 * проверка на ошибку - пользователь не найден
	 */
	void checkUserNotFoundException(Long userId, String errorMessage);

	/*
	 * проверка на ошибку - пользователь не найден
	 */
	void checkUserIsExistException(Long userId, String errorMessage);

	/*
	 * проверка на ошибку - пользователи уже друзья
	 */
	void checkUsersAreFriendsException(Long userOneId, Long userTwoId, String errorMessage);

	/*
	 * проверка на ошибку - пользователи не являются друзьями
	 */
	void checkUsersAreNotFriendsException(Long userOneId, Long userTwoId, String errorMessage);

	/*
	 * проверка на ошибку - неверное число для вывода рейтинговых фильмов
	 */
	void checkIllegalNumberFilmsCountException(int countFilms, String errorMessage);

	/*
	 * проверка на ошибку - пользователь уже ставил лайк фильму
	 */
	void checkUserAllreadySetLikeToFilmException(Long filmId, Long userId, String errorMessage);

	/*
	 * проверка на ошибку - пользователь уже ставил лайк фильму
	 */
	void checkUserDidntSetLikeToFilmException(Long filmId, Long userId, String errorMessage);

}