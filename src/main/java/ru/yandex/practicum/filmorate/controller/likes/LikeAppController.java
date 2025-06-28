package ru.yandex.practicum.filmorate.controller.likes;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ru.yandex.practicum.filmorate.model.film.Film;

public interface LikeAppController {

	/*
	 * поставить лайк фильму
	 */
	void setLike(@PathVariable Long id, @PathVariable Long userId);

	/*
	 * удалить лайк
	 */
	void removeLike(@PathVariable Long id, @PathVariable Long userId);

	/*
	 * вернуть ограниченный по count список рейтиноговых фильмов
	 */
	List<Film> getRatedFilms(@RequestParam(required = false, defaultValue = "10") Integer count);
}