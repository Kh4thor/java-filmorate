package ru.yandex.practicum.filmorate.service.users;

import java.util.List;

public interface UserAppService<T> {

	/*
	 * создать или обновить объект
	 */
	T createOrUpdate(T t);

	/*
	 * удалить объект по id
	 */
	T delete(long id);

	/*
	 * удалить все объекты
	 */
	void deleteAll();

	/*
	 * получить объект по id
	 */
	T get(long id);

	/*
	 * получить список всех объектов
	 */
	List<T> getAll();
}