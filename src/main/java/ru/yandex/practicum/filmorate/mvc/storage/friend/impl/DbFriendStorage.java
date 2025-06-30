package ru.yandex.practicum.filmorate.mvc.storage.friend.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.mvc.storage.friend.FriendAppStorage;

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
		String sql = "MERGE INTO friends (user_one_id, user_two_id, user_two_status) KEY (user_one_id, user_two_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, userOneId, userTwoId, true);
		return isUsersAssociatedAsFriends(userOneId, userTwoId);
	}

	@Override
	public boolean disassociateUserAsFriends(Long userOneId, Long userTwoId) {
		String sql = "MERGE INTO friends (user_one_id, user_two_id, user_two_status) KEY (user_one_id, user_two_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, userOneId, userTwoId, false);
		return !isUsersAssociatedAsFriends(userOneId, userTwoId);
	}

	@Override
	public boolean isUsersAssociatedAsFriends(Long userOneId, Long userTwoId) {
		String sql = "SELECT EXISTS (SELECT user_two_status FROM friends WHERE (user_one_id=? AND user_two_id=?))";
		return jdbcTemplate.queryForObject(sql, Boolean.class, userOneId, userTwoId);
	}

	@Override
	public void removeAllAssociatedFriendsOfUser(Long userOneId) {
		String sql = "UPDATE friends SET user_two_status=? WHERE user_one_id=?";
		jdbcTemplate.update(sql, false, userOneId);
	}

	@Override
	public void deleteUser(Long userId) {
		String sql = "DELETE FROM friends WHERE user_one_id=? OR user_two_id=?";
		jdbcTemplate.update(sql, userId, userId);
	}

	@Override
	public void clearStorage() {
		jdbcTemplate.update("TRUNCATE TABLE friends");
	}

	@Override
	public List<Long> getIdListOfAssociatedFriends(Long userId) {
		String sql = "SELECT user_two_id FROM friends WHERE user_one_id=? AND user_two_status=?";
		return jdbcTemplate.queryForList(sql, Long.class, userId, true);
	}

	@Override
	public List<Long> getIdListOfCommonFriends(Long userOneId, Long userTwoId) {

		// id-список общих друзей пользователей userOne и userTwo
		List<Long> commonFriendsIdList = new ArrayList<>();

		// id-список друзей пользователя userOne
		String sql = "SELECT user_two_id FROM friends WHERE user_one_id=? AND user_two_status=?";
		List<Long> userOneFriendsIdList = jdbcTemplate.queryForList(sql, Long.class, userOneId, true);

		// id-список друзей пользователя userTwo
		List<Long> userTwoFriendsList = jdbcTemplate.queryForList(sql, Long.class, userTwoId, true);

		// поиск общих друзей
		for (Long friendId : userOneFriendsIdList) {
			if (userTwoFriendsList.contains(friendId)) {
				commonFriendsIdList.add(friendId);
			}
		}
		return commonFriendsIdList;
	}
}
