package ru.yandex.practicum.filmorate.mvc.controller.genre.impl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.mvc.controller.genre.GenreAppController;
import ru.yandex.practicum.filmorate.mvc.service.genre.GenreAppService;

@Slf4j
@RestController
@RequestMapping("/genres")
public class GenreController implements GenreAppController {

	private final GenreAppService genreAppService;

	public GenreController(GenreAppService genreAppService) {
		this.genreAppService = genreAppService;
	}

	@Override
	@GetMapping("/{genreId}")
	public Genre getGenre(int genreId) {
		log.info("Начат процесс вызова жанра id=" + genreId);
		return genreAppService.getGenre(genreId);
	}

	@Override
	@GetMapping
	public List<Genre> getAllGenres() {
		log.info("Начат процесс вызова списка всех жанров");
		return genreAppService.getAllGenres();
	}

}
