package ru.yandex.practicum.filmorate.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.AppService;
import ru.yandex.practicum.filmorate.storage.AppStorage;

@Slf4j
@Service
public class UserService implements AppService<User> {

	private long id = 0;

	AppStorage<User> appStorage;

	public UserService(AppStorage<User> appStorage) {
		this.appStorage = appStorage;
	}

	/*
	 * создать или обновить пользователя
	 */
	@Override
	public User createOrUpdate(User user) {
		if (user.getId() == null || user.getId() == 0) {
			log.info("Начато создание пользователья. Получен объект {}", user);
			User createdUser = create(user);
			log.info("Пользователь {} успешно добавлен", createdUser);
			return createdUser;
		} else if (appStorage.isEntityExist(user)) {
			log.info("Начато обновление пользователя. Получен объект {}", user);
			User updatedUser = update(user);
			log.info("Пользователь {} успешно обновлен", updatedUser);
			return updatedUser;
		} else {
			return null;
		}
	}

	/*
	 * удалить пользователя по id
	 */
	@Override
	public User delete(long id) {
		return appStorage.remove(id);
	}

	/*
	 * удалить всех пользователей
	 */
	@Override
	public void deleteAll() {
		appStorage.clear();
	}

	/*
	 * получить пользователя по id
	 */
	@Override
	public User get(long id) {
		return appStorage.get(id);
	}

	/*
	 * получить список всех пользователей
	 */
	@Override
	public List<User> getAll() {
		return appStorage
				.getRepository()
				.values()
				.stream()
				.toList();
	}

	/*
	 * генератор id для нового пользователя
	 */
	private long generateId() {
		return ++id;
	}

	/*
	 * создать пользователя
	 */
	private User create(User user) {
		user.setId(generateId());
		return appStorage.add(user);
	}

	/*
	 * обновить пользоватедя
	 */
	private User update(User user) {
		return appStorage.add(user);
	}
}