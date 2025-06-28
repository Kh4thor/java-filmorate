package ru.yandex.practicum.filmorate.controller.likes.impl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.yandex.practicum.filmorate.controller.likes.LikeAppController;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.service.like.LikeAppService;

@RestController
@RequestMapping("/films")
public class LikeController implements LikeAppController {

	private final LikeAppService likeAppService;

	public LikeController(LikeAppService likeAppService) {
		this.likeAppService = likeAppService;
	}

	/*
	 * поставить лайк фильму
	 */
	@Override
	@PutMapping("/{id}/like/{userId}")
	public void setLike(Long id, Long userId) {
		likeAppService.setLike(id, userId);
	}

	/*
	 * удалить лайк
	 */
	@Override
	@DeleteMapping("/{id}/like/{userId}")
	public void removeLike(Long id, Long userId) {
		likeAppService.removeLike(id, userId);
	}

	/*
	 * вернуть ограниченный по count список рейтиноговых фильмов
	 */
	@Override
	@GetMapping("/popular")
	public List<Film> getRatedFilms(Integer count) {
		return likeAppService.getRatedFilms(count);
	}
}
