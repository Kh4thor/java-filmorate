package ru.yandex.practicum.filmorate.storage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.StorageService;

@Slf4j
@Service
public class UsersStorageService implements StorageService<User> {

	// хранилище пользователей
	private Map<Long, User> userStorage = new HashMap<>();

	// email-список пользователей. Связан с аннотацией @UniqueEmail в классе User.
	private final List<String> emailList = new ArrayList<>();

	/*
	 * добавить пользователя в хранилище
	 */
	@Override
	public User add(User user) {
		checkEmailList(user);
		userStorage.put(user.getId(), user);
		return user;
	}

	/*
	 * очистить хранилище пользователей
	 */
	@Override
	public void clear() {
		// очистить email-список
		emailList.clear();
		userStorage.clear();
	}

	/*
	 * проверить хранилище на наличие ключа id-пользователя
	 */
	@Override
	public boolean containsKey(Long id) {
		return userStorage.containsKey(id);
	}

	/*
	 * получить пользователя из хранилища
	 */
	@Override
	public User get(Long id) {
		if (userStorage.containsKey(id)) {
			return userStorage.get(id);
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
		return new HashMap<>(userStorage);
	}

	/*
	 * удалить пользователя из хранилища
	 */
	@Override
	public User remove(Long id) {
		if (userStorage.containsKey(id)) {
			// удаление email пользователя из email-списка
			User u = userStorage.get(id);
			emailList.remove(u.getEmail());
			return userStorage.remove(id);
		} else {
			log.warn("Пользователь с id={} не найден", id);
			return null;
		}
	}

	/*
	 * получить email-список всех пользователей
	 */
	public List<String> getEmailList() {
		return new ArrayList<>(emailList);
	}

	public boolean isUpdating(User user) {
		return userStorage.containsKey(user.getId());
	}

	/*
	 * отсеживание состояния email-списка
	 */
	private void checkEmailList(User user) {
		if (!emailList.contains(user.getEmail())) {
			addToEmailList(user);
		} else {
			updateEmailList(user);
		}
	}

	/*
	 * добавить пользователя в email-список
	 */
	private void addToEmailList(User user) {
		emailList.add(user.getEmail());
	}

	/*
	 * обновление email-списка при обновлении пользователя для метода update
	 * интерфейса AppService<User>. Связано с аннотацией @UniqueEmail класса User.
	 */
	private void updateEmailList(User user) {
		if (userStorage.containsKey(user.getId())) {
			User userFromStorage = userStorage.get(user.getId());
			emailList.remove(userFromStorage.getEmail());
			emailList.add(user.getEmail());
		}
	}
}