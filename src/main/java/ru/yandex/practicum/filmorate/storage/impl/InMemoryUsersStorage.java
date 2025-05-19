package ru.yandex.practicum.filmorate.storage.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.FilmAppStorage;

@Slf4j
@Service
public class InMemoryUsersStorage implements FilmAppStorage<User> {

	// хранилище пользователей
	private Map<Long, User> appStorage = new HashMap<>();

	/*
	 * добавить пользователя в хранилище
	 */
	@Override
	public User add(User user) {
		appStorage.put(user.getId(), user);
		return user;
	}

	/*
	 * очистить хранилище пользователей
	 */
	@Override
	public void clear() {
		appStorage.clear();
	}

	/*
	 * проверить хранилище на наличие ключа id-пользователя
	 */
	@Override
	public boolean isEntityExist(User user) {
		return isEntityExist(user.getId());
	}

	/*
	 * получить пользователя из хранилища
	 */
	@Override
	public User get(Long id) {
		if (appStorage.containsKey(id)) {
			return appStorage.get(id);
		} else {
			log.warn("Пользователь с id={} не найден", id);
			return null;
		}
	}

	/*
	 * получить хранилище пользователей
	 */
	@Override
	public Map<Long, User> getRepository() {
		return new HashMap<>(appStorage);
	}

	/*
	 * удалить пользователя из хранилища
	 */
	@Override
	public User remove(Long id) {
		if (appStorage.containsKey(id)) {
			User deletedUser = appStorage.remove(id);
			log.warn("Пользователь с id={} удален", id);
			return deletedUser;
		} else {
			log.warn("Пользователь с id={} не найден", id);
			return null;
		}
	}

	@Override
	public boolean isEntityExist(long id) {
		return appStorage.containsKey(id);
	}
}