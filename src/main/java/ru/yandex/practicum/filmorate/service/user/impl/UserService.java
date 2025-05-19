package ru.yandex.practicum.filmorate.service.user.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.user.UserAppService;
import ru.yandex.practicum.filmorate.storage.film.impl.InMemoryFilmsStorage;
import ru.yandex.practicum.filmorate.storage.friend.FriendsAppStorage;
import ru.yandex.practicum.filmorate.storage.user.UserAppStorage;

@Slf4j
@Service
public class UserService implements UserAppService<User> {

	private long id = 0;

	UserAppStorage<User> userAppStorage;
	FriendsAppStorage<User> associatedEntities;

	public UserService(UserAppStorage<User> userAppStorage, FriendsAppStorage<User> associatedEntities,
			InMemoryFilmsStorage inMemoryFilmsStorage) {
		this.userAppStorage = userAppStorage;
		this.associatedEntities = associatedEntities;
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
		} else if (userAppStorage.isEntityExist(user)) {
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
	public User delete(long userId) {
		associatedEntities.deleteEntityFromStorage(userId);
		return userAppStorage.remove(userId);
	}

	/*
	 * удалить всех пользователей
	 */
	@Override
	public void deleteAll() {
		associatedEntities.clearStorage();
		userAppStorage.clear();
	}

	/*
	 * получить пользователя по id
	 */
	@Override
	public User get(long userId) {
		return userAppStorage.get(userId);
	}

	/*
	 * получить список всех пользователей
	 */
	@Override
	public List<User> getAll() {
		return userAppStorage.getRepository().values().stream().toList();
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
		associatedEntities.addEntityToStorage(user.getId());
		return userAppStorage.add(user);
	}

	/*
	 * обновить пользоватедя
	 */
	private User update(User user) {
		return userAppStorage.add(user);
	}
}