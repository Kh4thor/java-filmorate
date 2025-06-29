package ru.yandex.practicum.filmorate.storage.users;

import java.util.List;

public interface UsersAppStorage<T> {

	/*
	 * добавить пользователя в хранилище
	 */
	T addUser(T t);

	/*
	 * очистить хранилище
	 */
	void clear();

	/*
	 * проверить хранилище на наличие пользователя
	 */
	boolean isUserExist(T t);

	/*
	 * проверить хранилище на наличие пользователя
	 */
	boolean isUserExist(Long id);

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
}
