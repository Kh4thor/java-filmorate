package ru.yandex.practicum.filmorate.exceptions;

import java.io.IOException;

public class FriendServiceException extends IOException {

	private static final long serialVersionUID = 1L;

	private long id;

	public FriendServiceException(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

}
