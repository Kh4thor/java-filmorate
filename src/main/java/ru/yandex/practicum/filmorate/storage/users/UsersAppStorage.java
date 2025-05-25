package ru.yandex.practicum.filmorate.storage.users;

import java.util.Map;

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
	boolean isUserExist(long id);

	/*
	 * получить пользователя из хранилища
	 */
	T getUser(Long id);

	/*
	 * получить хранилище
	 */
	Map<Long, T> getRepository();

	/*
	 * удалить пользователя из хранилища
	 */
	T removeUser(Long id);
}
