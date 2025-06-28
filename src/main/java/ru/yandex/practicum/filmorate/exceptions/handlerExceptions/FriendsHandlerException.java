package ru.yandex.practicum.filmorate.exceptions.handlerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.yandex.practicum.filmorate.exceptions.ErrorResponse;
import ru.yandex.practicum.filmorate.exceptions.friendExceptions.UsersAreAllreadyFriendsException;
import ru.yandex.practicum.filmorate.exceptions.friendExceptions.UsersAreNotFriendsException;

@RestControllerAdvice
public class FriendsHandlerException {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ErrorResponse handlerUsersAreAllreadyFriendsException(final UsersAreAllreadyFriendsException exception) {
		return new ErrorResponse(exception.getErrorMessage(), exception.getMessage());

	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ErrorResponse handlerUsersAreNotFriendsException(final UsersAreNotFriendsException exception) {
		return new ErrorResponse(exception.getErrorMessage(), exception.getMessage());
	}

}
