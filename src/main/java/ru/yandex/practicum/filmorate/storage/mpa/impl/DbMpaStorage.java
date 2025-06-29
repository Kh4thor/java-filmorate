package ru.yandex.practicum.filmorate.storage.mpa.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.model.film.Mpa;
import ru.yandex.practicum.filmorate.storage.mpa.MpaAppStorage;

@Repository
public class DbMpaStorage implements MpaAppStorage {

	private final JdbcTemplate jdbcTemplate;

	public DbMpaStorage(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Mpa getMpa(int mpaId) {
		String sql = "SELECT * FROM mpa WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new MpaRowMapper(), mpaId);
	}

	@Override
	public List<Mpa> getAllMpa() {
		String sql = "SELECT * FROM mpa";
		return jdbcTemplate.query(sql, new MpaRowMapper());
	}

	public static class MpaRowMapper implements RowMapper<Mpa> {

		@Override
		public Mpa mapRow(ResultSet rs, int rowNum) throws SQLException {
			Mpa mpa = new Mpa();
			mpa.setId(rs.getInt("id"));
			mpa.setName(rs.getString("name"));
			return mpa;
		}
	}
}
