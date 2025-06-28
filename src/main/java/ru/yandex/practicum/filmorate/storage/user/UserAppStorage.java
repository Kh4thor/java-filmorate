package ru.yandex.practicum.filmorate.storage.user;

import java.util.List;

public interface UserAppStorage<T> {

	/*
	 * добавить пользователя в хранилище
	 */
	T addUser(T t);

	/*
	 * обновить данные пользоватедя в хранилище
	 */
	T updateUser(T t);

	/*
	 * очистить хранилище
	 */
	void clear();

	/*
	 * получить пользователя из хранилища
	 */
	T getUser(Long id);

	/*
	 * удалить пользователя из хранилища
	 */
	T removeUser(Long id);

	/*
	 * получить список всех пользователей
	 */
	List<T> getAllUsers();

	/*
	 * проверка пользователя на его наличие в хранилище
	 */
	boolean isUserExist(Long userId);

}
