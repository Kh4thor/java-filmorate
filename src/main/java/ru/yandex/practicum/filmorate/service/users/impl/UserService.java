package ru.yandex.practicum.filmorate.service.users.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.users.UserAppService;
import ru.yandex.practicum.filmorate.storage.films.impl.InMemoryFilmsStorage;
import ru.yandex.practicum.filmorate.storage.friends.FriendsAppStorage;
import ru.yandex.practicum.filmorate.storage.users.UsersAppStorage;

@Slf4j
@Service
public class UserService implements UserAppService<User> {

	private long id = 0;

	UsersAppStorage<User> usersAppStorage;
	FriendsAppStorage friendsAppStorage;

	public UserService(UsersAppStorage<User> usersAppStorage, FriendsAppStorage friendsAppStorage,
			InMemoryFilmsStorage inMemoryFilmsStorage) {
		this.usersAppStorage = usersAppStorage;
		this.friendsAppStorage = friendsAppStorage;
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
		} else if (usersAppStorage.isEntityExist(user)) {
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
		friendsAppStorage.deleteEntityFromStorage(userId);
		return usersAppStorage.remove(userId);
	}

	/*
	 * удалить всех пользователей
	 */
	@Override
	public void deleteAll() {
		friendsAppStorage.clearStorage();
		usersAppStorage.clear();
	}

	/*
	 * получить пользователя по id
	 */
	@Override
	public User get(long userId) {
		return usersAppStorage.get(userId);
	}

	/*
	 * получить список всех пользователей
	 */
	@Override
	public List<User> getAll() {
		return usersAppStorage.getRepository().values().stream().toList();
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
		friendsAppStorage.addEntityToStorage(user.getId());
		return usersAppStorage.add(user);
	}

	/*
	 * обновить пользоватедя
	 */
	private User update(User user) {
		return usersAppStorage.add(user);
	}
}