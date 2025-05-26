package ru.yandex.practicum.filmorate.controller.friends.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.controller.friends.FriendsAppController;
import ru.yandex.practicum.filmorate.model.user.User;
import ru.yandex.practicum.filmorate.service.friends.FriendsAppService;

@Slf4j
@RestController
@RequestMapping("/users/{id}/friends")
public class FriendsController implements FriendsAppController {

	private final FriendsAppService friendsAppService;

	public FriendsController(FriendsAppService friendsAppService) {
		this.friendsAppService = friendsAppService;
	}

	/*
	 * добавить пользователя в друзья
	 */
	@Override
	@PutMapping("/{friendsId}")
	public ResponseEntity<String> addFriend(Long id, Long friendsId) {
		log.info("Начат процесс добавления друга. Получен id-пользователя=" + id + " и id-друга=" + friendsId);
		boolean isFriendsAssociated = friendsAppService.associateUsersAsFriends(id, friendsId);
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
	public ResponseEntity<String> deleteFriend(Long id, Long friendsId) {
		boolean isFriendsDisassociated = friendsAppService.disassociateUsersAsFriends(id, friendsId);
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
	public List<User> getListOfFriends(Long id) {
		return friendsAppService.getAllFriendsOfUser(id);
	}

	/*
	 * получить список общих друзей
	 */
	@Override
	@GetMapping("/common/{otherId}")
	public List<User> getCommonFriends(Long id, Long otherId) {
		return friendsAppService.getCommonFriendsOfUsers(id, otherId);
	}
}
