package ru.yandex.practicum.filmorate.controller.users.impl;

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
import ru.yandex.practicum.filmorate.controller.users.UsersAppController;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.users.UserAppService;

/*
 * User
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController implements UsersAppController<User> {

	private final UserAppService<User> userAppService;

	public UserController(UserAppService<User> appService) {
		this.userAppService = appService;
	}

	/*
	 * создать или обновить пользователя
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	@Override
	public ResponseEntity<User> createOrUpdate(User user) {
		User responseBody = userAppService.createOrUpdate(user);
		if (responseBody == null) {
			log.warn("Неверно задан запрос или параметры пользователя {}", user);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(responseBody);
		}
	}

	/*
	 * удалить пользователя по id
	 */
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<User> delete(long id) {
		log.info("Начато удаление пользователя. Получен id={}", id);
		User u = userAppService.delete(id);
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
		userAppService.deleteAll();
		log.info("Все пользователи удалены.");
	}

	/*
	 * получить пользователя по id
	 */
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<User> get(long id) {
		log.info("Начат вызов пользователя. Получен id={}", id);
		if (userAppService.delete(id) == null) {
			log.warn("Пользователь с id={} в списке не найден", id);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} else {
			log.info("Пользователь с id={} получен", id);
			return ResponseEntity.status(HttpStatus.OK).body(userAppService.get(id));
		}
	}

	/*
	 * получить список всех пользователей
	 */
	@GetMapping
	@Override
	public List<User> getAll() {
		log.info("Начато получение всех пользователей.");
		return userAppService.getAll();
	}
}
