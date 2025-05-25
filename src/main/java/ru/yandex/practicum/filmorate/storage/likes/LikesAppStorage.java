package ru.yandex.practicum.filmorate.storage.likes;

import java.util.List;

public interface LikesAppStorage {

	/*
	 * добавиить фильм в хранилище-счетчик
	 */
	boolean addToLikesStorage(long filmId);

	/*
	 * поставить лайк фильму
	 */
	boolean setLike(long filmId, long userId);

	/*
	 * проверка, ствавил ли пользователь лайк фильму
	 */
	boolean isUserSetLike(long filmId, long userId);

	/*
	 * удалить лайк пользователя
	 */
	boolean removeLike(long filmId, long userId);

	/*
	 * получить id-список с указанным количеством рейтиноговых фильмов
	 */
	List<Long> getIdListOfFilmsIdByRate(int countOfFilms);

	/*
	 * обнулить лайки для фильма
	 */
	boolean resetLikes(long filmId);

	/*
	 * удалить фильм из хранаилища-счетчика
	 */
	boolean deleteFromLikesStorage(long filmId);

	/*
	 * проверка на наличие id-фильма в хранилище-счетчике
	 */
	boolean isFilmInLikesAppStorageExist(long filmId);

	/*
	 * удалить все фильмы из хранилища-счетчика
	 */
	void deleteAllFilms();

}