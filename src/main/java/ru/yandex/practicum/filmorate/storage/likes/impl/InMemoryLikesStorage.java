package ru.yandex.practicum.filmorate.storage.likes.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.storage.likes.LikesAppStorage;

@Component
public class InMemoryLikesStorage implements LikesAppStorage {

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
	public boolean isFilmExist(long filmId) {
		return filmLikesMap.containsKey(filmId);
	}

	/*
	 * добавиить фильм в хранилище-счетчик
	 */
	@Override
	public boolean addFilm(long filmId) {
		List<Long> usersIdList = new ArrayList<>();
		filmLikesMap.put(filmId, usersIdList);
		return true;
	}

	/*
	 * удалить фильм из хранаилища-счетчика
	 */
	@Override
	public boolean deleteFilm(long filmId) {
		filmLikesMap.remove(filmId);
		return true;
	}

	/*
	 * поставить лайк фильму
	 */
	@Override
	public boolean setLike(long filmId, long userId) {
		List<Long> usersIdLikesList = filmLikesMap.get(filmId);
		usersIdLikesList.add(userId);
		sortFilmLikesMap();
		return true;
	}

	/*
	 * проверка, ствавил ли пользователь лайк фильму
	 */
	@Override
	public boolean isUserSetLike(long filmId, long userId) {
		List<Long> usersIdLikesList = filmLikesMap.get(filmId);
		return usersIdLikesList.contains(userId);
	}

	/*
	 * удалить лайк пользователя
	 */
	@Override
	public boolean removeLike(long filmId, long userId) {
		List<Long> usersIdLikesList = filmLikesMap.get(filmId);
		usersIdLikesList.remove(userId);
		return true;
	}

	/*
	 * получить id-список с указанным количеством рейтиноговых фильмов
	 */
	@Override
	public List<Long> getIdListOfFilmsIdByRate(int countOfFilms) {
		if (countOfFilms == 0) {
			countOfFilms = 10;
		}
		LinkedList<Long> idListOfFilmsIdByRate = new LinkedList<>();

		filmLikesMap
		.values()
		.stream()
		.flatMap(List::stream)
		.limit(countOfFilms)
		.forEach(idListOfFilmsIdByRate::add);

		return idListOfFilmsIdByRate;
	}

	/*
	 * обнулить количество лайков фильма
	 */
	@Override
	public boolean resetLikes(long filmId) {
		List<Long> usersIdLikesList = filmLikesMap.get(filmId);
		usersIdLikesList.clear();
		return true;
	}

	/*
	 * сортировка хранилища-счетчика лайков фильмов по количеству лайков
	 */
	private void sortFilmLikesMap() {
		List<Map.Entry<Long, List<Long>>> entries = new ArrayList<>(filmLikesMap.entrySet());

		// сортировка фильмов по количеству лайков у фильма (value.size())
		Collections.sort(entries, new Comparator<Map.Entry<Long, List<Long>>>() {
			@Override
			public int compare(Map.Entry<Long, List<Long>> a, Map.Entry<Long, List<Long>> b) {
				return Integer.compare(a.getValue().size(), b.getValue().size());
			}
		});

		// перезапись хранилища-счетчика с новым порядком
		for (Entry<Long, List<Long>> entry : entries) {
			filmLikesMap.put(entry.getKey(), entry.getValue());
		}
	}
}
