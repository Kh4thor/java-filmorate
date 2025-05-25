package ru.yandex.practicum.filmorate.controller.films.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.controller.films.FilmsAppController;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.service.films.FilmsAppService;

/*
 * Film
 */
@Slf4j
@RestController
@RequestMapping("/films")
public class FilmsController implements FilmsAppController<Film> {

	private FilmsAppService<Film> appService;

	public FilmsController(FilmsAppService<Film> filmService) {
		this.appService = filmService;
	}

	/*
	 * создать или обновить фильм
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	@Override
	public ResponseEntity<Film> createOrUpdate(Film film) {
		Film responseBody = appService.createOrUpdate(film);
		if (responseBody == null) {
			log.warn("Неверно задан запрос или параметры фильма {}", film);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(responseBody);
		}
	}

	/*
	 * удалить фильм по id
	 */
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<Film> delete(long id) {
		log.info("Начато удаление фильма. Получен id={}", id);
		if (appService.delete(id) == null) {
			log.warn("Фильм с id={} в списке не найден", id);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			log.info("Фильм с id={} удален", id);
			return ResponseEntity.status(HttpStatus.OK).body(appService.delete(id));
		}
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
	public ResponseEntity<Film> get(long id) {
		log.info("Начат вызов фильма. Получен id={}", id);
		if (appService.get(id) == null) {
			log.warn("Фильм с id={} в списке не найден", id);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			log.info("Фильм с id={} получен", id);
			return ResponseEntity.status(HttpStatus.OK).body(appService.get(id));
		}
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
