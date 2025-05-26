package ru.yandex.practicum.filmorate.storage.likes;

import java.util.List;

public interface LikesAppStorage {

	/*
	 * добавиить фильм в хранилище-счетчик
	 */
	boolean addFilm(Long filmId);

	/*
	 * поставить лайк фильму
	 */
	boolean setLike(Long filmId, Long userId);

	/*
	 * проверка, ствавил ли пользователь лайк фильму
	 */
	boolean isUserSetLike(Long filmId, Long userId);

	/*
	 * удалить лайк пользователя
	 */
	boolean removeLike(Long filmId, Long userId);

	/*
	 * получить id-список с указанным количеством рейтиноговых фильмов
	 */
	List<Long> getIdListOfFilmsIdByRate(int countOfFilms);

	/*
	 * обнулить лайки для фильма
	 */
	boolean resetLikes(Long filmId);

	/*
	 * удалить фильм из хранаилища-счетчика
	 */
	boolean deleteFilm(Long filmId);

	/*
	 * проверка на наличие id-фильма в хранилище-счетчике
	 */
	boolean isFilmExist(Long filmId);

	/*
	 * удалить все фильмы из хранилища-счетчика
	 */
	void deleteAllFilms();

}