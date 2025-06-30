package ru.yandex.practicum.filmorate.mvc.storage.genre.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.mvc.storage.genre.GenreAppStorage;
import ru.yandex.practicum.filmorate.utills.mappers.GenreRowMapper;

@Repository
@Component
public class DbGenreStorage implements GenreAppStorage<Genre> {

	private final JdbcTemplate jdbcTemplate;

	public DbGenreStorage(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Genre getGenre(int genreId) {
		String sql = "SELECT * FROM genres WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new GenreRowMapper(), genreId);
	}

	@Override
	public List<Genre> getAllGenres() {
		String sql = "SELECT * FROM genres";
		return jdbcTemplate.query(sql, new GenreRowMapper());
	}
}
