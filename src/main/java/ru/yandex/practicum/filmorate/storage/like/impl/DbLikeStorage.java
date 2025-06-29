package ru.yandex.practicum.filmorate.storage.like.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.storage.like.LikeAppStorage;

public class DbLikeStorage implements LikeAppStorage {

	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public boolean addFilm(Film film) {
		String sql = "INSERT INTO film_likes (film_id, user_id, like_status) VALUES(?, ?, ?)";
		jdbcTemplate.update(sql);
		return isFilmExist(film.getId());
	}

	@Override
	public boolean setLike(Long filmId, Long userId) {
		String sql = "UPDATE film_likes SET likes=? WHERE film_id=?, user_id=?";
		jdbcTemplate.update(sql, 1, filmId, userId);
		return isUserSetLike(filmId, userId);
	}

	@Override
	public boolean isUserSetLike(Long filmId, Long userId) {
		String sql = "SELECT like_status FROM film_likes WHERE film_id=? AND user_id=?";
		int likeValue = jdbcTemplate.queryForObject(sql, Integer.class, filmId, userId);
		return likeValue == 1;
	}

	@Override
	public boolean removeLike(Long filmId, Long userId) {
		String sql = "UPDATE film_likes SET like_status=? WHERE film_id=? AND user_id=?";
		jdbcTemplate.update(sql, 0, filmId, userId);
		return isUserSetLike(filmId, userId);
	}

	@Override
	public List<Long> getIdListOfFilmsIdByRate(int countOfFilms) {
		String sql = "SELECT film_id FROM film_likes GROUP BY film_id ORDER BY COUNT(film_id) DESC LIMIT=?";
		return jdbcTemplate.queryForList(sql, Long.class, countOfFilms);
	}

	@Override
	public boolean resetLikes(Long filmId) {
		String sql = "UPDATE film_likes SET like_status=?";
		jdbcTemplate.update(sql);
		return true;
	}

	@Override
	public boolean deleteFilm(Long filmId) {
		String sql = "DELETE FROM film_likes WHERE film_id=?";
		jdbcTemplate.update(sql);
		return true;
	}

	@Override
	public boolean isFilmExist(Long filmId) {
		String sql = "SELECT EXISTS (SELECT id FROM films_likes WHERE id=?)";
		return jdbcTemplate.queryForObject(sql, Boolean.class);
	}

	@Override
	public void deleteAllFilms() {
		String sql = "TRUNCATE TABLE film_likes";
		jdbcTemplate.update(sql);
	}
}
