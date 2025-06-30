package ru.yandex.practicum.filmorate.mvc.storage.mpa.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.model.mpa.Mpa;
import ru.yandex.practicum.filmorate.mvc.storage.mpa.MpaAppStorage;
import ru.yandex.practicum.filmorate.utills.mappers.MpaRowMapper;

@Repository
@Component
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
}
