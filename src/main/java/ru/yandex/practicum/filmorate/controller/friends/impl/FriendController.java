package ru.yandex.practicum.filmorate.controller.friends.impl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.controller.friends.FriendAppController;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.friend.FriendAppService;

@Slf4j
@RestController
@RequestMapping("/users/{id}/friends")
public class FriendController implements FriendAppController {

	private final FriendAppService friendAppService;

	public FriendController(FriendAppService friendAppService) {
		this.friendAppService = friendAppService;
	}

	/*
	 * добавить пользователя в друзья
	 */
	@Override
	@PutMapping("/{friendsId}")
	public void addFriend(Long id, Long friendsId) {
		log.info("Начат процесс добавления друга. Получен id-пользователя=" + id + " и id-друга=" + friendsId);
		friendAppService.associateUsersAsFriends(id, friendsId);

	}

	/*
	 * удалить пользователя из друзей
	 */
	@Override
	@DeleteMapping("/{friendsId}")
	public void deleteFriend(Long id, Long friendsId) {
		friendAppService.disassociateUsersAsFriends(id, friendsId);
	}

	/*
	 * получить друзей пользователя
	 */
	@Override
	@GetMapping
	public List<User> getListOfFriends(Long id) {
		return friendAppService.getAllFriendsOfUser(id);
	}

	/*
	 * получить список общих друзей
	 */
	@Override
	@GetMapping("/common/{otherId}")
	public List<User> getCommonFriends(Long id, Long otherId) {
		return friendAppService.getCommonFriendsOfUsers(id, otherId);
	}
}
