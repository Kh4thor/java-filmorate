package ru.yandex.practicum.filmorate.controller.likes;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import ru.yandex.practicum.filmorate.model.film.Film;

public interface LikesAppController {

	/*
	 * поставить лайк фильму
	 */
	void setLike(@PathVariable long id, @PathVariable long userId);

	/*
	 * удалить лайк
	 */
	void removeLike(@PathVariable long id, @PathVariable long userId);

	/*
	 * вернуть список из первых count-фильмов
	 */
	List<Film> getRatedFilms(@PathVariable int count);

}