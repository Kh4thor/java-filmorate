package ru.yandex.practicum.filmorate.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse userNotFound(final FriendServiceException e) {
		return new ErrorResponse("Пользователя с id=" + e.getId()
				+ " невозможно добавить в друзья. Такой пользователь не зарегестрирован.");
	}
	
	

}