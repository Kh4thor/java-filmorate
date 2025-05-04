package ru.yandex.practicum.filmorate.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.AppService;
import ru.yandex.practicum.filmorate.storage.StorageService;

@Slf4j
@Service
public class UserService implements AppService<User> {

	private long id = 0;

	StorageService<User> storageService;

	public UserService(StorageService<User> storageService) {
		this.storageService = storageService;
	}

	/*
	 * создать или обновить пользвателя
	 */
	@Override
	public User createOrUpdate(User user) {
		if (user.getId() == null || user.getId() == 0) {
			logCreation(user);
			return create(user);
		} else {
			logUpdating(user);
			return update(user);
		}
	}

	/*
	 * удалить пользователя по id
	 */
	@Override
	public User delete(long id) {
		return storageService.remove(id);
	}

	/*
	 * удалить всех пользователей
	 */
	@Override
	public void deleteAll() {
		storageService.clear();
	}

	/*
	 * получить пользователя по id
	 */
	@Override
	public User get(long id) {
		return storageService.get(id);
	}

	/*
	 * получить список всех пользователей
	 */
	@Override
	public List<User> getAll() {
		return storageService.getRepository().values().stream().toList();
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
		return storageService.add(user);
	}

	/*
	 * обновить пользоватедя
	 */
	private User update(User user) {
		return storageService.add(user);
	}

	private void logUpdating(User user) {
		log.info("Начато создание фильма. Получен объект {}", user);
		if (create(user) == null) {
			log.warn("Неверно заданы параметры фильма {}", user);
		} else {
			log.info("Фильм {} успешно обновлен", user);
		}
	}

	private void logCreation(User user) {
		log.info("Начато обновление фильма. Получен объект {}", user);
		if (create(user) == null) {
			log.warn("Неверно заданы параметры фильма {}", user);
		} else {
			log.info("Фильм {} успешно добавлен", user);
		}
	}
}