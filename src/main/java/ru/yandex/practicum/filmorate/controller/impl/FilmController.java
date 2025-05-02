package ru.yandex.practicum.filmorate.controller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.controller.AppController;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.service.AppService;

/*
 * Film
 */
@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController implements AppController<Film> {

	private AppService<Film> appService;

	public FilmController(AppService<Film> filmService) {
		this.appService = filmService;
	}

	/*
	 * создать/обновить фильм
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	@Override
	public Film createOrUpdate(Film film) {
		return appService.createOrUpdate(film);
	}

	/*
	 * удалить фильм по id
	 */
	@DeleteMapping("/{id}")
	@Override
	public Film delete(long id) {
		log.info("Начато удаление фильма. Получен id={}", id);
		return appService.delete(id);
	}

	/*
	 * удалить все фильмы
	 */
	@DeleteMapping()
	@Override
	public void deleteAll() {
		log.info("Начато удаление всех фильмов");
		appService.deleteAll();
	}

	/*
	 * получить фльм по id
	 */
	@GetMapping("/{id}")
	@Override
	public Film get(long id) {
		log.info("Начато получение фильма");
		return appService.get(id);
	}

	/*
	 * получить список всех фильмов
	 */
	@GetMapping()
	@Override
	public List<Film> getAll() {
		log.info("Начато получение всех фильмов");
		return appService.getAll();
	}
}
