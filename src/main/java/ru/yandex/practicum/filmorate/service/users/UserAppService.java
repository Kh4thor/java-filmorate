package ru.yandex.practicum.filmorate.service.users;

import java.util.List;

public interface UserAppService<T> {

	/*
	 * создать или обновить пользователя
	 */
	T createOrUpdateUser(T t);

	/*
	 * удалить пользователя
	 */
	T deleteUser(Long id);

	/*
	 * удалить всех пользователей
	 */
	void deleteAllUsers();

	/*
	 * получить пользователя
	 */
	T getUser(Long id);

	/*
	 * получить список всех пользователей
	 */
	List<T> getAllUsers();
}