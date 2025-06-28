package ru.yandex.practicum.filmorate.storage.friend.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import ru.yandex.practicum.filmorate.model.friend.FriendshipStatus;
import ru.yandex.practicum.filmorate.storage.friend.FriendAppStorage;

public class DbFriendsSrorage implements FriendAppStorage {

	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public void addUser(Long id) {
	}

	@Override
	public boolean associateUsersAsFriends(Long userOneId, Long userTwoId) {
		String sql = "UPDATE friends SET userOneStatus=? WHERE (userOneId=? AND userTwoId=?)";
		jdbcTemplate.update(sql, FriendshipStatus.FRIEND, userOneId, userTwoId);
		jdbcTemplate.update(sql, FriendshipStatus.FRIEND, userTwoId, userOneId);
		return true;
	}

	@Override
	public boolean disassociateUserAsFriends(Long userOneId, Long userTwoId) {
		String sql = "UPDATE friends SET userOneStatus=? WHERE (userOneId=? AND userTwoId=?)";
		jdbcTemplate.update(sql, FriendshipStatus.NOT_FRIEND, userOneId, userTwoId);
		jdbcTemplate.update(sql, FriendshipStatus.REQUEST_TO_FRIENDS, userTwoId, userOneId);
		return true;
	}

	@Override
	public boolean isUsersAssociatedAsFriends(Long userOneId, Long userTwoId) {
		String sql = "SELECT userOneId FROM friends WHERE (userOneId=? AND userTwoId=? AND userOneStatus=?)";
		List<Long> userOneIdList = jdbcTemplate.queryForList(sql, Long.class, userOneId, userTwoId,
				FriendshipStatus.FRIEND);
		return !userOneIdList.isEmpty();
	}

	@Override
	public void removeAllAssociatedFriendsOfUser(Long UserOneId) {
		String sql = "UPDATE friends  (userOneStatus=?) WHERE userOneId=?";
		jdbcTemplate.update(sql, FriendshipStatus.NOT_FRIEND, UserOneId);
	}

	@Override
	public void deleteUser(Long userId) {
		String sql = "DELETE FROM friends WHERE userOneid=? AND userTwoid=?";
		jdbcTemplate.update(sql, userId, userId);
	}

	@Override
	public void clearStorage() {
		jdbcTemplate.update("DROP TABLE friends");
		jdbcTemplate.update("CREATE TABLE friends");
	}

	@Override
	public List<Long> getIdListOfAssociatedFriends(Long userId) {
		String sql = "SELECT userTwoId FROM friends WHERE userOneId=?";
		return jdbcTemplate.queryForList(sql, Long.class, userId);
	}

	@Override
	public List<Long> getIdListOfCommonFriends(Long userOneId, Long userTwoId) {

		// id-список общих друзей пользователей userOne и userTwo
		List<Long> commonFriendsIdList = new ArrayList<>();

		// id-список друзей пользователя userOne
		String sqlOne = "SELECT userTwoId FROM friends WHERE userOneId=? AND userOneStatus=?";
		List<Long> userOneFriendsIdList = jdbcTemplate.queryForList(sqlOne, Long.class, userOneId,
				FriendshipStatus.FRIEND);

		// id-список друзей пользователя userTwo
		String sqlTwo = "SELECT userTwoId FROM friends WHERE userOneId=? AND userOneStatus=?";
		List<Long> userTwoFriendsList = jdbcTemplate.queryForList(sqlTwo, Long.class, userTwoId,
				FriendshipStatus.FRIEND);

		// поиск общих друзей
		for (Long friendId : userOneFriendsIdList) {
			if (userTwoFriendsList.contains(friendId)) {
				commonFriendsIdList.add(friendId);
			}
		}
		return commonFriendsIdList;
	}

	@Override
	public void sendRequestToUserForFriendship(Long requesterUserId, Long userId) {
		String sql = "UPDATE friends SET userOneStatus=?";
		jdbcTemplate.update(sql, FriendshipStatus.REQUEST_TO_FRIENDS);
	}
}
