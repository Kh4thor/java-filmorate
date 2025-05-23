package ru.yandex.practicum.filmorate.controller.friend.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.UsersAreAllreadyFriendsException;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.friend.impl.FriendService;

@RestController
@RequestMapping("/users/{id}/friends")
public class FriendController {

	private final FriendService friendService;

	public FriendController(FriendService friendService) {
		this.friendService = friendService;
	}

	@PutMapping("/{friendsId}")
	public ResponseEntity<User> addFriend(long id, long friendsId) throws UsersAreAllreadyFriendsException {
		try {
			friendService.associateUsersAsFriends(id, friendsId);
			return ResponseEntity.status(HttpStatus.OK);

		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} catch (UsersAreAllreadyFriendsException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
}
