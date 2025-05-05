package ru.yandex.practicum.filmorate.controller.impl;

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
import ru.yandex.practicum.filmorate.controller.AppController;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.AppService;

/*
 * User
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController implements AppController<User> {

	private AppService<User> appService;

	public UserController(AppService<User> appService) {
		this.appService = appService;
	}

	/*
	 * создать или обновить пользователя
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	@Override
	public ResponseEntity<User> createOrUpdate(User user) {
		User userResponse = appService.createOrUpdate(user);
		if (userResponse == null) {
			log.warn("Неверный запрос или параметры пользователя {}", user);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(userResponse);
		}
	}

	/*
	 * удалить пользователя по id
	 */
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<User> delete(long id) {
		log.info("Начато удаление фильма. Получен id={}", id);
		User u = appService.delete(id);
		if (u == null) {
			log.warn("Фильм с id={} в списке не найден", id);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			log.info("Фильм с id={} удален", id);
			return ResponseEntity.status(HttpStatus.OK).body(u);
		}
	}

	/*
	 * удалить всех пользователей
	 */
	@DeleteMapping()
	@Override
	public void deleteAll() {
		log.info("Начато удаление всех пользователей.");
		appService.deleteAll();
		log.info("Все пользователи удалены.");
	}

	/*
	 * получить пользователя по id
	 */
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<User> get(long id) {
		log.info("Начат вызов пользователя. Получен id={}", id);
		if (appService.delete(id) == null) {
			log.warn("Пользователь с id={} в списке не найден", id);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			log.info("Пользователь с id={} получен", id);
			return ResponseEntity.status(HttpStatus.OK).body(appService.get(id));
		}
	}

	/*
	 * получить список всех пользователей
	 */
	@GetMapping
	@Override
	public List<User> getAll() {
		log.info("Начато получение всех пользователей.");
		return appService.getAll();
	}
}
