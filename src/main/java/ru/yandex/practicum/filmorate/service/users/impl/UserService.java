package ru.yandex.practicum.filmorate.service.users.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.ExceptionsAppChecker;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.users.UserAppService;
import ru.yandex.practicum.filmorate.storage.films.impl.InMemoryFilmsStorage;
import ru.yandex.practicum.filmorate.storage.friends.FriendsAppStorage;
import ru.yandex.practicum.filmorate.storage.users.UsersAppStorage;

@Slf4j
@Service
public class UserService implements UserAppService<User> {

	private Long id = 0L;

	UsersAppStorage<User> usersAppStorage;
	FriendsAppStorage friendsAppStorage;
	ExceptionsAppChecker exceptionsAppChecker;

	public UserService(UsersAppStorage<User> usersAppStorage, FriendsAppStorage friendsAppStorage,
			InMemoryFilmsStorage inMemoryFilmsStorage, ExceptionsAppChecker exceptionsChecker) {
		this.usersAppStorage = usersAppStorage;
		this.friendsAppStorage = friendsAppStorage;
		this.exceptionsAppChecker = exceptionsChecker;
	}

	/*
	 * создать или обновить пользователя
	 */
	@Override
	public User createOrUpdateUser(User user) {
		if (user.getId() == null || user.getId() == 0) {
			log.info("Начато создание пользователья. Получен объект {}", user);
			User createdUser = create(user);
			log.info("Пользователь {} успешно добавлен", createdUser);
			return createdUser;
		} else if (usersAppStorage.isUserExist(user)) {
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
	public User deleteUser(Long userId) {
		String errorMessage = "Невозможно удалить пользователя";
		friendsAppStorage.deleteUser(userId);
		exceptionsAppChecker.checkUserNotFoundException(userId, errorMessage);
		return usersAppStorage.removeUser(userId);
	}

	/*
	 * удалить всех пользователей
	 */
	@Override
	public void deleteAllUsers() {
		friendsAppStorage.clearStorage();
		usersAppStorage.clear();
	}

	/*
	 * получить пользователя по id
	 */
	@Override
	public User getUser(Long userId) {
		return usersAppStorage.getUser(userId);
	}

	/*
	 * получить список всех пользователей
	 */
	@Override
	public List<User> getAllUsers() {
		return usersAppStorage.getRepository().values().stream().toList();
	}

	/*
	 * генератор id для нового пользователя
	 */
	private Long generateId() {
		return ++id;
	}

	/*
	 * создать пользователя
	 */
	private User create(User user) {
		String errorMessage = "Невозможно создать пользователя";
		user.setId(generateId());
		exceptionsAppChecker.checkUserIsExistException(user.getId(), errorMessage);
		friendsAppStorage.addUser(user.getId());
		return usersAppStorage.addUser(user);
	}

	/*
	 * обновить пользоватедя
	 */
	private User update(User user) {
		String errorMessage = "Невозможно обновить пользователя";
		exceptionsAppChecker.checkUserNotFoundException(user.getId(), errorMessage);
		friendsAppStorage.addUser(user.getId());
		return usersAppStorage.addUser(user);
	}
}