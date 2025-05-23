package ru.yandex.practicum.filmorate.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

	public UserNotFoundException(HttpStatusCode status, String reason) {
		super(status, reason);
	}

	private static final long serialVersionUID = 1L;
	private long userId;

//	public UserNotFoundException(long userId) {
//		super("Пользователь c id=" + userId + " не найден");
//		this.userId = userId;
//	}

	public long getUserId() {
		return userId;
	}

}
