package ru.yandex.practicum.filmorate.service.likes;

import java.util.List;

import ru.yandex.practicum.filmorate.model.film.Film;

public interface LikesAppService {

	/*
	 * поставить лайк фильму
	 */
	void setLike(long filmId, long userId);

	/*
	 * удалить лайк
	 */
	void removeLike(long filmId, long userId);

	/*
	 * вернуть список из первых count-фильмов по количеству лайков
	 */
	List<Film> getRatedFilms(int count);

}