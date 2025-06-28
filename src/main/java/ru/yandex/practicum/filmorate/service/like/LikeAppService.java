package ru.yandex.practicum.filmorate.service.like;

import java.util.List;

import ru.yandex.practicum.filmorate.model.film.Film;

public interface LikeAppService {

	/*
	 * поставить лайк фильму
	 */
	void setLike(Long filmId, Long userId);

	/*
	 * удалить лайк
	 */
	void removeLike(Long filmId, Long userId);

	/*
	 * вернуть список из первых count-фильмов по количеству лайков
	 */
	List<Film> getRatedFilms(Integer count);

}