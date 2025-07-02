package ru.yandex.practicum.filmorate.mvc.controller.likes.impl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.mvc.controller.likes.LikeAppController;
import ru.yandex.practicum.filmorate.mvc.service.like.LikeAppService;

@Slf4j
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
		log.info("Начат процесс записи лайка фильму пользователем. id-фильма=" + id + " id-пользователя=" + userId);
		likeAppService.setLike(id, userId);
	}

	/*
	 * удалить лайк
	 */
	@Override
	@DeleteMapping("/{id}/like/{userId}")
	public void removeLike(Long id, Long userId) {
		log.info("Начат процесс удаления лайка фильму пользователем. id-фильма=" + id + " id-пользователя=" + userId);
		likeAppService.removeLike(id, userId);
	}

	/*
	 * вернуть ограниченный по count список рейтиноговых фильмов
	 */
	@Override
	@GetMapping("/popular")
	public List<Film> getRatedFilms(Integer count) {
		log.info("Начат процесс получения рейтинговых фильмов");
		return likeAppService.getRatedFilms(count);
	}
}
