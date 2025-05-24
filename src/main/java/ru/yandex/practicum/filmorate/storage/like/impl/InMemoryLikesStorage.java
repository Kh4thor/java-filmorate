package ru.yandex.practicum.filmorate.storage.like.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

@Component
public class InMemoryLikesStorage {

	/*
	 * хранилище-счетчик лайков фильмов;
	 */
	// key - id-фильма;
	// value - список id-пользователей, поставивших лайк;
	// value.size() - количества лайков у фильма;
	private Map<Long, List<Long>> filmLikesMap = new LinkedHashMap<>();

	/*
	 * сортировка хранилища-счетчика лайков фильмов по количеству лайков
	 */
	private void sortFilmLikesMap() {
		List<Map.Entry<Long, List<Long>>> entries = new ArrayList<>(filmLikesMap.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<Long, List<Long>>>() {
			public int compare(Map.Entry<Long, List<Long>> a, Map.Entry<Long, List<Long>> b) {
				return Integer.compare(a.getValue().size(), b.getValue().size());
			}
		});

		for (Entry<Long, List<Long>> entry : entries) {
			filmLikesMap.put(entry.getKey(), entry.getValue());
		}
	}

	/*
	 * поставить лайк фильму
	 */
	public boolean setLike(long filmId, long userId) {
		List<Long> usersIdLikesList = filmLikesMap.get(filmId);
		usersIdLikesList.add(userId);
		sortFilmLikesMap();
		return true;
	}

	/*
	 * проверка, ствавил ли пользователь лайк фильму
	 */
	public boolean isUserSetLike(long filmId, long userId) {
		List<Long> usersIdLikesList = filmLikesMap.get(filmId);
		return usersIdLikesList.contains(userId);
	}

	/*
	 * удалить лайк пользователя
	 */
	public void removeLike(long filmId, long userId) {
		List<Long> usersIdLikesList = filmLikesMap.get(filmId);
		usersIdLikesList.remove(userId);
	}

	/*
	 * получить id-список с указанным количеством рейтиноговых фильмов
	 */
	public List<Long> getIdListOfFilmsIdByRate(long filmId, long countOfFilms) {
		LinkedList<Long> idListOfFilmsIdByRate = new LinkedList<>();
		filmLikesMap.values().stream().flatMap(List::stream).limit(countOfFilms).forEach(idListOfFilmsIdByRate::add);
		return idListOfFilmsIdByRate;

	}

	/*
	 * получить id-список по-умолчанию
	 */
	public List<Long> getIdListOfFilmsIdByRate(long filmId) {
		int countOfFilms = 10;
		LinkedList<Long> idListOfFilmsIdByRate = new LinkedList<>();
		filmLikesMap.values().stream().flatMap(List::stream).limit(countOfFilms).forEach(idListOfFilmsIdByRate::add);
		return idListOfFilmsIdByRate;

	}

	public void resetLikes(long filmId) {
		List<Long> usersIdLikesList = filmLikesMap.get(filmId);
		usersIdLikesList.clear();
	}
}
