package ru.yandex.practicum.filmorate.storage.users.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.storage.users.UsersAppStorage;

@Repository
@RequiredArgsConstructor
@Component
//@Qualifier
public class DbUsersStorage implements UsersAppStorage<User> {

//	private UserMapper userMapper = new UserMapper();

	private final JdbcTemplate jdbcTemplate;

	@Override
	public User addUser(User user) {
		String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getId(), user.getName(), user.getLogin(), user.getEmail(), user.getBirthday());
		return user;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	/*
	 * получить пользователя
	 */
	@Override
	public User getUser(Long userId) {
		String sql = "SELECT * FROM users WHERE user_id=?";
		return jdbcTemplate.query(sql, new UserMapper(), userId).stream().findAny().orElse(null);

	}

	@Override
	public User removeUser(Long userId) {
		String sql = "DELETE FROM users WHERE user_id=?";
		User user = jdbcTemplate.query(sql, new UserMapper(), userId).stream().findAny().orElse(null);
		jdbcTemplate.update(sql, userId);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	static class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {

			Long id = rs.getLong("user_id");
			String name = rs.getString("name");
			String login = rs.getString("login");
			String email = rs.getString("email");
			LocalDate birthday = rs.getDate("birthday").toLocalDate();

			return User.builder().id(id).name(name).login(login).email(email).birthday(birthday).build();
		}
	}
}
