package ru.yandex.practicum.filmorate.controller.users.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
public class UsersController implements UsersAppController<User> {

	private final UserAppService<User> userAppService;

	public UsersController(UserAppService<User> appService) {
		this.userAppService = appService;
	}

	/*
	 * создать или обновить пользователя
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	@Override
	public ResponseEntity<User> createOrUpdateUser(User user) {
		User responseBody = userAppService.createOrUpdateUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	}

	/*
	 * удалить пользователя по id
	 */
	@DeleteMapping("/{id}")
	@Override
	public void deleteUser(Long id) {
		log.info("Начато удаление пользователя. Получен id={}", id);
		userAppService.deleteUser(id);
	}

	/*
	 * удалить всех пользователей
	 */
	@DeleteMapping()
	@Override
	public void deleteAllUsers() {
		log.info("Начато удаление всех пользователей.");
		userAppService.deleteAllUsers();
		log.info("Все пользователи удалены.");
	}

	/*
	 * получить пользователя по id
	 */
	@GetMapping("/{id}")
	@Override
	public User getUser(Long id) {
		log.info("Начат вызов пользователя. Получен id={}", id);
		return userAppService.getUser(id);
	}

	/*
	 * получить список всех пользователей
	 */
	@GetMapping
	@Override
	public List<User> getAllUsers() {
		log.info("Начато получение всех пользователей.");
		return userAppService.getAllUsers();
	}
}
