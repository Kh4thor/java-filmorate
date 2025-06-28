package ru.yandex.practicum.filmorate.storage.user.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.user.UserAppStorage;

@Repository
@Component
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
		String sql = "INSERT INTO users (id, name, login, email, birthday) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getId(), user.getName(), user.getLogin(), user.getEmail(), user.getBirthday());
		return getUser(user.getId());
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
		return jdbcTemplate.queryForObject(sql, new UserMapper(), userId);
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
		return jdbcTemplate.query(sql, new UserMapper());
	}

	static class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setName(rs.getString("name"));
			user.setLogin(rs.getString("login"));
			user.setEmail(rs.getString("email"));
			user.setBirthday(rs.getDate("birthday").toLocalDate());
			return user;
		}
	}
}
