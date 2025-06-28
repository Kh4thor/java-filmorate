package ru.yandex.practicum.filmorate.controller.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

public interface UserAppController<T> {

	/*
	 * создать или обновить пользователя
	 */
	ResponseEntity<T> createOrUpdateUser(@Valid @RequestBody T t);

	/*
	 * удалить пользователя по id
	 */
	void deleteUser(@PathVariable Long id);

	/*
	 * удалить всех пользователей
	 */
	void deleteAllUsers();

	/*
	 * получить пользователя по id
	 */
	T getUser(@PathVariable Long id);

	/*
	 * получить список всех пользователей
	 */
	List<T> getAllUsers();
}