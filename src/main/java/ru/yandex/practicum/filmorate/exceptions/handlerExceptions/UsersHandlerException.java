package ru.yandex.practicum.filmorate.exceptions.handlerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.yandex.practicum.filmorate.exceptions.ErrorResponse;
import ru.yandex.practicum.filmorate.exceptions.userExceptions.UserAllreadyExistException;
import ru.yandex.practicum.filmorate.exceptions.userExceptions.UserNotFoundException;

@RestControllerAdvice
public class UsersHandlerException {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse handlerUserNotFoundException(final UserNotFoundException exception) {
		return new ErrorResponse(exception.getError(), exception.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ErrorResponse handlerUserNotFoundException(final UserAllreadyExistException exception) {
		return new ErrorResponse(exception.getError(), exception.getMessage());
	}
}
