package ru.yandex.practicum.filmorate.controller.friend.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.controller.friend.FriendAppController;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.friend.FriendsAppService;

@RestController
@Slf4j
@RequestMapping("/users/{id}/friends")
public class FriendController implements FriendAppController {

	private final FriendsAppService friendService;

	public FriendController(FriendsAppService friendService) {
		this.friendService = friendService;
	}

	/*
	 * добавить пользователя в друзья
	 */
	@Override
	@PutMapping("/{friendsId}")
	public ResponseEntity<String> addFriend(long id, long friendsId) {
		log.info("Начат процесс добавления друга. Получен id-пользователя=" + id + " и id-друга=" + friendsId);
		boolean isFriendsAssociated = friendService.associateUsersAsFriends(id, friendsId);
		if (isFriendsAssociated) {
			log.info("Пользователи id=" + id + " и id=" + friendsId + " объединены в друзья");
			return ResponseEntity.status(HttpStatus.OK)
					.body("Пользователи c id=" + id + " и id=" + friendsId + " объединены в друзья");
		} else {
			log.info("Неудачная попытка объеденить пользрвателей id=" + id + " и id =" + friendsId + " в друзья");
			return null;
		}
	}

	/*
	 * удалить пользователя из друзей
	 */
	@Override
	@DeleteMapping("/{friendsId}")
	public ResponseEntity<String> deleteFriend(long id, long friendsId) {
		boolean isFriendsDisassociated = friendService.disassociateUsersAsFriends(id, friendsId);
		if (isFriendsDisassociated) {
			log.info("Пользователи c id=" + id + " и id=" + friendsId + " больше не друзья");
			return ResponseEntity.status(HttpStatus.OK)
					.body("Пользователи c id=" + id + " и id=" + friendsId + " больше не друзья");
		} else {
			log.info("Неудачная попытка убрать из друзей друг друга пользователей с id=" + id + " и id =" + friendsId);
			return null;
		}
	}

	/*
	 * получить друзей пользователя
	 */
	@Override
	@GetMapping
	public List<User> getListOfFriends(long id) {
		return friendService.getAllFriendsOfUser(id);
	}

	/*
	 * получить список общих друзей
	 */
	@Override
	@GetMapping("/common/{otherId}")
	public List<User> getCommonFriends(long id, long otherId) {
		return friendService.getCommonFriendsOfUsers(id, otherId);
	}
}
