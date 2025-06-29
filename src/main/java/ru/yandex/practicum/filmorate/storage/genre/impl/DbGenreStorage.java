package ru.yandex.practicum.filmorate.storage.genre.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.model.film.Genre;
import ru.yandex.practicum.filmorate.storage.genre.GenreAppStorage;

@Repository
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

	public static class GenreRowMapper implements RowMapper<Genre> {

		@Override
		public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
			Genre genre = new Genre();
			genre.setId(rs.getInt("id"));
			genre.setName(rs.getString("name"));
			return genre;
		}
	}

}
