package ru.yandex.practicum.filmorate.controller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
	public User createOrUpdate(User user) {
		return appService.createOrUpdate(user);
	}

	/*
	 * удалить пользователя по id
	 */
	@DeleteMapping("/{id}")
	@Override
	public User delete(long id) {
		log.info("Начато удаление пользователя. Получен id объекта id={}", id);
		return appService.delete(id);
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
	public User get(long id) {
		log.info("Начато получение пользователя. Получен id объекта id={}", id);
		return appService.get(id);
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
