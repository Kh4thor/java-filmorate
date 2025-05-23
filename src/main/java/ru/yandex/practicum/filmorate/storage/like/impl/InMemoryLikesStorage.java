package ru.yandex.practicum.filmorate.storage.like.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ru.yandex.practicum.filmorate.storage.like.LikesAppStorage;

public class InMemoryLikesStorage implements LikesAppStorage {

	private Map<Long, Long> filmLikesMap = new TreeMap<>();

	@Override
	public void setLike(long filmId) {
		long likes = filmLikesMap.getOrDefault(filmId, 0L);
		filmLikesMap.put(filmId, ++likes);
	}

	@Override
	public void removeLike() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Long> getFilmsIdByRate(long filmId) {
		return null;
	}

	@Override
	public List<Long> getFilmsIdByRate(long filmId, int countOfFilms) {
		return null;
	}

	@Override
	public void resetLikes(long filmId) {
		filmLikesMap.put(filmId, 0L);
	}

}
