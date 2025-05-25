package ru.yandex.practicum.filmorate.exceptions.handlerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.yandex.practicum.filmorate.exceptions.ErrorResponse;
import ru.yandex.practicum.filmorate.exceptions.filmExceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.likeExceptions.UserAllreadySetLikeToFilmException;
import ru.yandex.practicum.filmorate.exceptions.likeExceptions.UserDidntSetLikeToFilmException;

@RestControllerAdvice
public class FilmsHandlerException {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse handlerFilmNotFoundException(final FilmNotFoundException exception) {
		return new ErrorResponse(exception.getError(), exception.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ErrorResponse handlerUserAllreadySetLikeToFilmException(final UserAllreadySetLikeToFilmException exception) {
		return new ErrorResponse(exception.getError(), exception.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ErrorResponse handlerUserDidntSetLikeToFilmException(final UserDidntSetLikeToFilmException exception) {
		return new ErrorResponse(exception.getError(), exception.getMessage());
	}
}
