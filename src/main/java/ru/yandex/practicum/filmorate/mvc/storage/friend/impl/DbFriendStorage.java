package ru.yandex.practicum.filmorate.mvc.storage.friend.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.mvc.storage.friend.FriendAppStorage;
import ru.yandex.practicum.filmorate.utills.mappers.UserRowMapper;

@Repository("dbFriendStorage")
public class DbFriendStorage implements FriendAppStorage {

	private final JdbcTemplate jdbcTemplate;

	public DbFriendStorage(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addUser(Long userOneid) {
	}

	@Override
	public boolean associateUsersAsFriends(Long userOneId, Long userTwoId) {
		String sql = "MERGE INTO friends (user_one_id, user_two_id, user_two_status) "
					+ "KEY (user_one_id, user_two_id) "
					+ "VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, userOneId, userTwoId, true);
		return isUsersAssociatedAsFriends(userOneId, userTwoId);
	}

	@Override
	public boolean disassociateUserAsFriends(Long userOneId, Long userTwoId) {
		String sql = "MERGE INTO friends (user_one_id, user_two_id, user_two_status) "
					+ "KEY (user_one_id, user_two_id) " 
					+ "VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, userOneId, userTwoId, false);
		return !isUsersAssociatedAsFriends(userOneId, userTwoId);
	}

	@Override
	public boolean isUsersAssociatedAsFriends(Long userOneId, Long userTwoId) {
		String sql = "SELECT EXISTS (SELECT 1 "
					+ "FROM friends " 
					+ "WHERE (user_one_id=? AND user_two_id=?))";
		return jdbcTemplate.queryForObject(sql, Boolean.class, userOneId, userTwoId);
	}

	@Override
	public void removeAllAssociatedFriendsOfUser(Long userOneId) {
		String sql = "UPDATE friends "
					+ "SET user_two_status=? "
					+ "WHERE user_one_id=?";
		jdbcTemplate.update(sql, false, userOneId);
	}

	@Override
	public void deleteUser(Long userId) {
		String sql = "DELETE FROM friends "
					+ "WHERE user_one_id=? OR user_two_id=?";
		jdbcTemplate.update(sql, userId, userId);
	}

	@Override
	public void clearStorage() {
		jdbcTemplate.update("TRUNCATE TABLE friends");
	}

	@Override
	public List<User> getAllFriendsOfUser(Long userId) {
		String getAllFriendsOfUserSql = "SELECT u.* "
										+ "FROM users AS u "
										+ "WHERE u.id IN (SELECT f.user_two_id AS friend_id "
														+ "FROM friends AS f "
														+ "WHERE f.user_two_status=true AND f.user_one_id=?)";
		return  jdbcTemplate.query(getAllFriendsOfUserSql, new UserRowMapper(), userId);
	}

	@Override
	public List<User> getCommonFriends(Long userOneId, Long userTwoId) {
		String getCommonFriendsSql = "SELECT u.* "
									+ "FROM users AS u "
									+ "JOIN friends AS f1 ON u.id = f1.user_two_id "
									+ "JOIN friends AS f2 ON u.id = f2.user_two_id "
									+ "WHERE f1.user_one_id = ? AND f2.user_one_id=?";
		return jdbcTemplate.query(getCommonFriendsSql, new UserRowMapper(), userOneId, userTwoId);
	}
}