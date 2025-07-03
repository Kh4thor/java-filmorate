package ru.yandex.practicum.filmorate.mvc.storage.user.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.mvc.storage.user.UserAppStorage;
import ru.yandex.practicum.filmorate.utills.mappers.UserRowMapper;

@Repository("dbUserStorage")
public class DbUserStorage implements UserAppStorage<User> {

	private final JdbcTemplate jdbcTemplate;

	public DbUserStorage(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean isUserExist(Long userId) {
		String sql = "SELECT EXISTS (SELECT id FROM users WHERE id=?)";
		return jdbcTemplate.queryForObject(sql, Boolean.class, userId);
	}

	@Override
	public User addUser(User user) {
		String sql = "INSERT INTO users (name, login, email, birthday) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getName(), user.getLogin(), user.getEmail(), user.getBirthday());

		String userIdSql = "SELECT MAX (id) FROM users";
		Long userId = jdbcTemplate.queryForObject(userIdSql, Long.class);
		return getUser(userId);
	}

	@Override
	public User updateUser(User user) {
		String sql = "UPDATE users SET name=?, login=?, email=?, birthday=? WHERE id=?";
		jdbcTemplate.update(sql, user.getName(), user.getLogin(), user.getEmail(), user.getBirthday(), user.getId());
		return getUser(user.getId());
	}

	@Override
	public void clear() {
		jdbcTemplate.update("TRUNCATE TABLE users");
	}

	@Override
	public User getUser(Long userId) {
		String sql = "SELECT * FROM users WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
	}

	@Override
	public User removeUser(Long userId) {
		String sql = "DELETE FROM users WHERE id=?";
		User user = getUser(userId);
		jdbcTemplate.update(sql, userId);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM users";
		return jdbcTemplate.query(sql, new UserRowMapper());
	}
}
