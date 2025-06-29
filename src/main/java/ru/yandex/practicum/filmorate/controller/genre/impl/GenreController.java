package ru.yandex.practicum.filmorate.controller.genre.impl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.controller.genre.GenreAppController;
import ru.yandex.practicum.filmorate.model.film.Genre;
import ru.yandex.practicum.filmorate.service.genre.GenreAppService;

@Slf4j
@RestController
@RequestMapping("/genres")
public class GenreController implements GenreAppController<Genre> {

	private final GenreAppService<Genre> genreAppService;

	public GenreController(GenreAppService<Genre> genreAppService) {
		this.genreAppService = genreAppService;
	}

	@Override
	@GetMapping("/{genreId}")
	public Genre getGenre(int genreId) {
		return genreAppService.getGenre(genreId);
	}

	@Override
	@GetMapping
	public List<Genre> getAllGenres() {
		return genreAppService.getAllGenres();
	}

}
