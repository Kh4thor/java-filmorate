package ru.yandex.practicum.filmorate.service.likes;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import ru.yandex.practicum.filmorate.model.film.Film;

public interface LikesAppService {

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
	List<Film> getRatedFilms(@RequestParam("count") int count);

}