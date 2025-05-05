package ru.yandex.practicum.filmorate.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	 * создать или обновить фильм
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	@Override
	public ResponseEntity<Film> createOrUpdate(Film film) {
		Film filmResponse = appService.createOrUpdate(film);
		if (filmResponse == null) {
			log.warn("Неверно заданы параметры фильма {}", film);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(filmResponse);
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
