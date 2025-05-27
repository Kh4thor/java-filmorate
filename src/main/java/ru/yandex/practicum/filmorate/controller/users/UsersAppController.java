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
	ResponseEntity<T> createOrUpdateUser(@Valid @RequestBody T t);

	/*
	 * удалить объект по id
	 */
	void deleteUser(@PathVariable Long id);

	/*
	 * удалить все объекты
	 */
	void deleteAllUsers();

	/*
	 * получить объект по id
	 */
	ResponseEntity<T> getUser(@PathVariable Long id);

	/*
	 * получить список всех объектов
	 */
	List<T> getAllUsers();
}