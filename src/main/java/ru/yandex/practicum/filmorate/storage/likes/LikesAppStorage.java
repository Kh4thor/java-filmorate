package ru.yandex.practicum.filmorate.storage.likes;

import java.util.List;

public interface LikesAppStorage {

	void resetLikes(long filmId);

	void setLike(long filmId);

	void removeLike();

	List<Long> getFilmsIdByRate(long filmId);

	List<Long> getFilmsIdByRate(long filmId, int countOfFilms);
}
