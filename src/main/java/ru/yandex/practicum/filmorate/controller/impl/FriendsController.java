package ru.yandex.practicum.filmorate.controller.impl;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.controller.FriendAppController;
import ru.yandex.practicum.filmorate.exceptions.FriendServiceException;
import ru.yandex.practicum.filmorate.exceptions.UserServiceException;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.friend.FriendsAppService;

@Slf4j
@RestController
@RequestMapping("/users/{id}")
public class FriendsController implements FriendAppController<User> {

	FriendsAppService friendAppService;

	public FriendsController(FriendsAppService friendAppService) {
		this.friendAppService = friendAppService;
	}

	@Override
	@PutMapping("/friends/{friendId}")
	public void associateUsersAsFriends(long id, long friendId) throws FriendServiceException, UserServiceException {
		friendAppService.associateUsersAsFriends(id, friendId);
	}

}