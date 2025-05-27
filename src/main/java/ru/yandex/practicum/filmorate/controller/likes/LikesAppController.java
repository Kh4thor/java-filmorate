package ru.yandex.practicum.filmorate.controller.likes;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import ru.yandex.practicum.filmorate.model.film.Film;

public interface LikesAppController {

	/*
	 * поставить лайк фильму
	 */
	void setLike(@PathVariable Long id, @PathVariable Long userId);

	/*
	 * удалить лайк
	 */
	void removeLike(@PathVariable Long id, @PathVariable Long userId);

	/*
	 * вернуть список из первых count-фильмов
	 */
	List<Film> getRatedFilms(Integer count);

}