package ru.yandex.practicum.filmorate.service.likes;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.exceptions.filmExceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.films.FilmsAppStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikesAppStorage;
import ru.yandex.practicum.filmorate.storage.users.UsersAppStorage;

@Component
public class LikeService {

	LikesAppStorage likesAppStorage;
	UsersAppStorage<User> usersAppStorage;
	FilmsAppStorage<Film> filmsAppStorage;

	public LikeService(LikesAppStorage likesAppStorage, UsersAppStorage<User> usersAppStorage,
			FilmsAppStorage<Film> filmsAppStorage) {
		this.likesAppStorage = likesAppStorage;
		this.usersAppStorage = usersAppStorage;
		this.filmsAppStorage = filmsAppStorage;
	}

	/*
	 * поставить лайк фильму
	 */
	public void setLikeToFilm(long filmId, long userId) {
		likesAppStorage.setLike(filmId, userId);
	}

	/*
	 * проверка фильма на отсутсвие фильма в хранилище-счетчике лайков
	 */
	private void checkFilmNotFoundException(long filmId, String error) {
		if (!likesAppStorage.isFilmInLikesAppStorageExist(filmId)) {
			throw new FilmNotFoundException(filmId, error);
		}
	}

}
