package ru.yandex.practicum.filmorate.exceptions.userExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.yandex.practicum.filmorate.exceptions.ErrorResponse;

@RestControllerAdvice
public class UsersHandlerException {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ErrorResponse handlerUsersAreAllreadyFriendsException(UsersAreAllreadyFriendsException exception) {
		return new ErrorResponse(exception.getError(), exception.getMessage());

	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse handlerThereIsNoSuchUserInTheStorage(UserNotFoundException exception) {
		return new ErrorResponse(exception.getError(), exception.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ErrorResponse handlerUsersAreNotFriendsException(UsersAreNotFriendsException exception) {
		return new ErrorResponse(exception.getError(), exception.getMessage());
	}

}
