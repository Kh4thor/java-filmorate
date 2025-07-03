package ru.yandex.practicum.filmorate.mvc.storage.genre.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.mvc.storage.genre.GenreAppStorage;
import ru.yandex.practicum.filmorate.utills.mappers.GenreRowMapper;

@Repository("dbGenreStorage")
public class DbGenreStorage implements GenreAppStorage {

	private final JdbcTemplate jdbcTemplate;

	public DbGenreStorage(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Genre getGenre(int genreId) {
		String sql = "SELECT * FROM genres WHERE id=?";
		try {
			return jdbcTemplate.queryForObject(sql, new GenreRowMapper(), genreId);
		} catch (EmptyResultDataAccessException exception) {
			return null;
		}
	}

	@Override
	public List<Genre> getAllGenres() {
		String sql = "SELECT * FROM genres";
		return jdbcTemplate.query(sql, new GenreRowMapper());
	}

	@Override
	public boolean isGenreExist(int genreId) {
		String sql = "SELECT EXISTS (SELECT id FROM genres WHERE id=?)";
		return jdbcTemplate.queryForObject(sql, Boolean.class, genreId);
	}

}
