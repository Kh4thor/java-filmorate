package ru.yandex.practicum.filmorate.service.films;

import java.util.List;

public interface FilmsAppService<T> {

	/*
	 * создать или обновить объект
	 */
	T createOrUpdate(T t);

	/*
	 * удалить объект по id
	 */
	T delete(Long id);

	/*
	 * удалить все объекты
	 */
	void deleteAll();

	/*
	 * получить объект по id
	 */
	T get(Long id);

	/*
	 * получить список всех объектов
	 */
	List<T> getAll();
}