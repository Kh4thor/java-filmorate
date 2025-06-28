package ru.yandex.practicum.filmorate.controller.film.impl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.controller.film.FilmAppController;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.service.film.FilmAppService;

/*
 * Film
 */
@Slf4j
@RestController
@RequestMapping("/films")
public class FilmsController implements FilmAppController<Film> {

	private final FilmAppService<Film> filmAppService;

	public FilmsController(FilmAppService<Film> filmAppService) {
		this.filmAppService = filmAppService;
	}

	/*
	 * создать или обновить фильм
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	@Override
	public Film createOrUpdateFilm(Film film) {
		return filmAppService.createOrUpdateFilm(film);
	}

	/*
	 * удалить фильм по id
	 */
	@DeleteMapping("/{id}")
	@Override
	public void deleteFilm(Long id) {
		log.info("Начато удаление фильма. Получен id={}", id);
		filmAppService.deleteFilm(id);
	}

	/*
	 * удалить все фильмы
	 */
	@DeleteMapping()
	@Override
	public void deleteAllFilms() {
		log.info("Начато удаление всех фильмов");
		filmAppService.deleteAllFilms();
	}

	/*
	 * получить фльм по id
	 */
	@GetMapping("/{id}")
	@Override
	public Film getFilm(Long id) {
		log.info("Начат вызов фильма. Получен id={}", id);
		return filmAppService.getFilm(id);
	}

	/*
	 * получить список всех фильмов
	 */
	@GetMapping()
	@Override
	public List<Film> getAllFilms() {
		log.info("Начато получение всех фильмов");
		return filmAppService.getAllFilms();
	}
}
