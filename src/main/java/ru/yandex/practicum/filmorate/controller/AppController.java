package ru.yandex.practicum.filmorate.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

public interface AppController<T> {

	/*
	 * создать или обновить объект
	 */
	T createOrUpdate(@Valid @RequestBody T t);

	/*
	 * удалить объект по id
	 */
	T delete(@PathVariable long id);

	/*
	 * удалить все объекты
	 */
	void deleteAll();

	/*
	 * получить объект по id
	 */
	T get(@PathVariable long id);

	/*
	 * получить список всех объектов
	 */
	List<T> getAll();
}