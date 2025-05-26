package ru.yandex.practicum.filmorate.controller.users;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

public interface UsersAppController<T> {

	/*
	 * создать или обновить объект
	 */
	ResponseEntity<T> createOrUpdate(@Valid @RequestBody T t);

	/*
	 * удалить объект по id
	 */
	ResponseEntity<T> delete(@PathVariable Long id);

	/*
	 * удалить все объекты
	 */
	void deleteAll();

	/*
	 * получить объект по id
	 */
	ResponseEntity<T> get(@PathVariable Long id);

	/*
	 * получить список всех объектов
	 */
	List<T> getAll();
}