package ru.yandex.practicum.filmorate.mvc.storage.like.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.mvc.storage.like.LikeAppStorage;

@Repository("dbLikeStorage")
public class DbLikeStorage implements LikeAppStorage {

	private final JdbcTemplate jdbcTemplate;

	public DbLikeStorage(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean addFilm(Film film) {
		return true;
	}

	@Override
	public boolean setLike(Long filmId, Long userId) {
		String sql = "MERGE INTO films_likes (film_id, user_id, like_status) KEY (film_id, user_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, filmId, userId, true);
		return isUserSetLike(filmId, userId);
	}

	@Override
	public boolean isUserSetLike(Long filmId, Long userId) {
		String sql = "SELECT EXISTS (SELECT 1 FROM films_likes WHERE (film_id=? AND user_id=?))";
		return jdbcTemplate.queryForObject(sql, Boolean.class, filmId, userId);
	}

	@Override
	public boolean removeLike(Long filmId, Long userId) {
		String sql = "UPDATE films_likes SET like_status=? WHERE film_id=? AND user_id=?";
		jdbcTemplate.update(sql, false, filmId, userId);
		return !isUserSetLike(filmId, userId);
	}

	@Override
	public List<Long> getIdListOfFilmsIdByRate(int countOfFilms) {
		String sql = "SELECT film_id FROM films_likes GROUP BY film_id ORDER BY COUNT(film_id) DESC LIMIT "
				+ countOfFilms;
		return jdbcTemplate.queryForList(sql, Long.class);
	}

	@Override
	public boolean resetLikes(Long filmId) {
		String sql = "UPDATE films_likes SET like_status=?";
		jdbcTemplate.update(sql, false);
		return true;
	}

	@Override
	public boolean deleteFilm(Long filmId) {
		String sql = "DELETE FROM films_likes WHERE film_id=?";
		jdbcTemplate.update(sql, filmId);
		return true;
	}

	@Override
	public boolean isFilmExist(Long filmId) {
		String sql = "SELECT EXISTS (SELECT 1 FROM films_likes WHERE film_id=?)";
		return jdbcTemplate.queryForObject(sql, Boolean.class, filmId);
	}

	@Override
	public void deleteAllFilms() {
		String sql = "TRUNCATE TABLE films_likes";
		jdbcTemplate.update(sql);
	}
}
