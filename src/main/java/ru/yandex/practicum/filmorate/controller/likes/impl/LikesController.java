package ru.yandex.practicum.filmorate.controller.likes.impl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.yandex.practicum.filmorate.controller.likes.LikesAppController;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.service.likes.LikesAppService;

@RestController
@RequestMapping("/films")
public class LikesController implements LikesAppController {

	private final LikesAppService likesAppService;

	public LikesController(LikesAppService likesAppService) {
		this.likesAppService = likesAppService;
	}

	/*
	 * поставить лайк фильму
	 */
	@Override
	@PutMapping("/{id}/like/{userId}")
	public void setLike(Long id, Long userId) {
		likesAppService.setLike(id, userId);
	}

	/*
	 * удалить лайк
	 */
	@Override
	@DeleteMapping("/{id}/like/{userId}")
	public void removeLike(Long id, Long userId) {
		likesAppService.removeLike(id, userId);
	}

	/*
	 * вернуть список из первых count-фильмов
	 */
	@Override
	@GetMapping("/popular?count={count}")
	public List<Film> getRatedFilms(int count) {
		return likesAppService.getRatedFilms(count);
	}

}
