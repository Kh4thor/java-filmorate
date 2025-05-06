package ru.yandex.practicum.filmorate.storage.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.AppStorage;

@Slf4j
@Service
public class UsersStorage implements AppStorage<User> {

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
	public boolean containsKey(Long id) {
		return appStorage.containsKey(id);
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
}