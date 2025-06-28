package ru.yandex.practicum.filmorate.service.user.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.exceptionsChecker.ExceptionAppChecker;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.user.UserAppService;
import ru.yandex.practicum.filmorate.storage.friend.FriendAppStorage;
import ru.yandex.practicum.filmorate.storage.user.UserAppStorage;

@Slf4j
@Service
public class UserService implements UserAppService<User> {

	private Long id = 0L;

	private final UserAppStorage<User> userAppStorage;
	private final FriendAppStorage friendAppStorage;
	private final ExceptionAppChecker exceptionsAppChecker;

	public UserService(UserAppStorage<User> usersAppStorage, FriendAppStorage friendAppStorage,
			ExceptionAppChecker exceptionsChecker) {
		this.userAppStorage = usersAppStorage;
		this.friendAppStorage = friendAppStorage;
		this.exceptionsAppChecker = exceptionsChecker;
	}

	/*
	 * создать или обновить пользователя
	 */
	@Override
	public User createOrUpdateUser(User user) {
		if (user.getId() == null || user.getId() == 0) {
			log.info("Начато создание пользователя. Получен объект {}", user);
			User createdUser = create(user);
			log.info("Пользователь {} успешно добавлен", createdUser);
			return createdUser;
		} else {
			log.info("Начато обновление пользователя. Получен объект {}", user);
			User updatedUser = update(user);
			log.info("Пользователь {} успешно обновлен", updatedUser);
			return updatedUser;
		}
	}

	/*
	 * удалить пользователя по id
	 */
	@Override
	public User deleteUser(Long userId) {
		String errorMessage = "Невозможно удалить пользователя";
		friendAppStorage.deleteUser(userId);
		exceptionsAppChecker.checkUserNotFoundException(userId, errorMessage);
		return userAppStorage.removeUser(userId);
	}

	/*
	 * удалить всех пользователей
	 */
	@Override
	public void deleteAllUsers() {
		friendAppStorage.clearStorage();
		userAppStorage.clear();
	}

	/*
	 * получить пользователя по id
	 */
	@Override
	public User getUser(Long userId) {
		String errorMessage = "Невозможно получить пользователя";
		exceptionsAppChecker.checkUserNotFoundException(userId, errorMessage);
		return userAppStorage.getUser(userId);
	}

	/*
	 * получить список всех пользователей
	 */
	@Override
	public List<User> getAllUsers() {
		return userAppStorage.getAllUsers();
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
		friendAppStorage.addUser(user.getId());
		return userAppStorage.addUser(user);
	}

	/*
	 * обновить пользоватедя
	 */
	private User update(User user) {
		String errorMessage = "Невозможно обновить пользователя";
		exceptionsAppChecker.checkUserNotFoundException(user.getId(), errorMessage);
		friendAppStorage.addUser(user.getId());
		return userAppStorage.updateUser(user);
	}
}