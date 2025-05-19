package ru.yandex.practicum.filmorate.controller.film;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

public interface FilmAppController<T> {

	/*
	 * создать или обновить объект
	 */
	ResponseEntity<T> createOrUpdate(@Valid @RequestBody T t);

	/*
	 * удалить объект по id
	 */
	ResponseEntity<T> delete(@PathVariable long id);

	/*
	 * удалить все объекты
	 */
	void deleteAll();

	/*
	 * получить объект по id
	 */
	ResponseEntity<T> get(@PathVariable long id);

	/*
	 * получить список всех объектов
	 */
	List<T> getAll();
}