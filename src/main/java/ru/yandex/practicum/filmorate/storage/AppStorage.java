package ru.yandex.practicum.filmorate.storage;

import java.util.Map;

public interface AppStorage<T> {

	/*
	 * добавить объект в хранилище
	 */
	T add(T t);

	/*
	 * очистить хранилище
	 */
	void clear();

	/*
	 * проверить хранилище на наличие ключа-id объекта
	 */
	boolean isEntityExist(T t);

	/*
	 * получить объект из хранилища
	 */
	T get(Long id);

	/*
	 * получить хранилище
	 */
	Map<Long, T> getRepository();

	/*
	 * удалить объект из хранилища
	 */
	T remove(Long id);
}
