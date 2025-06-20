package ru.yandex.practicum.filmorate.exceptions.handlerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.yandex.practicum.filmorate.exceptions.ErrorResponse;
import ru.yandex.practicum.filmorate.exceptions.likesExceptions.UserAllreadySetLikeToFilmException;
import ru.yandex.practicum.filmorate.exceptions.likesExceptions.UserDidntSetLikeToFilmException;

@RestControllerAdvice
public class LikesHandlerException {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.OK)
	public ErrorResponse handlerUserAllreadySetLikeToFilmException(final UserAllreadySetLikeToFilmException exception) {
		return new ErrorResponse(exception.getErrorMessage(), exception.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.OK)
	public ErrorResponse handlerUserDidntSetLikeToFilmException(final UserDidntSetLikeToFilmException exception) {
		return new ErrorResponse(exception.getErrorMessage(), exception.getMessage());
	}
}
