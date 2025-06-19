package ru.yandex.practicum.filmorate.storage.users.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.users.UsersAppStorage;

@Slf4j
//@Component
public class InMemoryUsersStorage implements UsersAppStorage<User> {

	// хранилище пользователей
	private Map<Long, User> usersStorageMap = new HashMap<>();

	/*
	 * добавить пользователя в хранилище
	 */
	@Override
	public User addUser(User user) {
		usersStorageMap.put(user.getId(), user);
		return user;
	}

	/*
	 * очистить хранилище пользователей
	 */
	@Override
	public void clear() {
		usersStorageMap.clear();
	}

	/*
	 * получить пользователя из хранилища
	 */
	@Override
	public User getUser(Long id) {
		if (usersStorageMap.containsKey(id)) {
			return usersStorageMap.get(id);
		} else {
			log.warn("Пользователь с id={} не найден", id);
			return null;
		}
	}

	/*
	 * удалить пользователя из хранилища
	 */
	@Override
	public User removeUser(Long id) {
		if (usersStorageMap.containsKey(id)) {
			User deletedUser = usersStorageMap.remove(id);
			log.warn("Пользователь с id={} удален", id);
			return deletedUser;
		} else {
			log.warn("Пользователь с id={} не найден", id);
			return null;
		}
	}

//	/*
//	 * проверить хранилище на наличие пользователя
//	 */
//	@Override
//	public boolean isUserExist(Long id) {
//		return usersStorageMap.containsKey(id);
//	}

	/*
	 * получить всех пользователей
	 */
	@Override
	public List<User> getAllUsers() {
		return usersStorageMap.values().stream().toList();
	}
}