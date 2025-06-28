package ru.yandex.practicum.filmorate.storage.like.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.storage.like.LikeAppStorage;

@Component
public class InMemoryLikeStorage implements LikeAppStorage {

	/*
	 * хранилище-счетчик лайков фильмов;
	 */
	// key - id-фильма;
	// value - список id-пользователей, поставивших лайк;
	// value.size() - количества лайков у фильма;

	private Map<Long, List<Long>> filmLikesMap = new LinkedHashMap<>();

	/*
	 * удалить все фильмы из хранилища-счетчика
	 */
	@Override
	public void deleteAllFilms() {
		filmLikesMap.clear();
	}

	@Override
	public boolean isFilmExist(Long filmId) {
		return filmLikesMap.containsKey(filmId);
	}

	/*
	 * добавиить фильм в хранилище-счетчик
	 */
	@Override
	public boolean addFilm(Film film) {
		filmLikesMap.put(film.getId(), new ArrayList<>());
		return true;
	}

	/*
	 * удалить фильм из хранаилища-счетчика
	 */
	@Override
	public boolean deleteFilm(Long filmId) {
		filmLikesMap.remove(filmId);
		return true;
	}

	/*
	 * поставить лайк фильму
	 */
	@Override
	public boolean setLike(Long filmId, Long userId) {
		List<Long> usersIdLikesList = filmLikesMap.get(filmId);
		usersIdLikesList.add(userId);
		sortFilmLikesMap();
		return true;
	}

	/*
	 * проверка, ствавил ли пользователь лайк фильму
	 */
	@Override
	public boolean isUserSetLike(Long filmId, Long userId) {
		List<Long> usersIdLikesList = filmLikesMap.get(filmId);
		return usersIdLikesList.contains(userId);
	}

	/*
	 * удалить лайк пользователя
	 */
	@Override
	public boolean removeLike(Long filmId, Long userId) {
		List<Long> usersIdLikesList = filmLikesMap.get(filmId);
		usersIdLikesList.remove(userId);
		sortFilmLikesMap();
		return true;
	}

	/*
	 * получить id-список с указанным количеством рейтиноговых фильмов
	 */
	@Override
	public List<Long> getIdListOfFilmsIdByRate(int countOfFilms) {
		return new LinkedList<>(filmLikesMap.keySet());
	}

	/*
	 * обнулить количество лайков фильма
	 */
	@Override
	public boolean resetLikes(Long filmId) {
		List<Long> usersIdLikesList = filmLikesMap.get(filmId);
		usersIdLikesList.clear();
		return true;
	}

	private void sortFilmLikesMap() {
		filmLikesMap = filmLikesMap
				.entrySet()
				.stream()
				.sorted(Comparator.comparingInt((Map.Entry<Long, List<Long>> entry) -> entry.getValue().size()).reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
}