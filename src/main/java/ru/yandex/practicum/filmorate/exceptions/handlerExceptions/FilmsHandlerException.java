package ru.yandex.practicum.filmorate.exceptions.handlerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.yandex.practicum.filmorate.exceptions.ErrorResponse;
import ru.yandex.practicum.filmorate.exceptions.filmsExceptions.FilmAllreadyExistException;
import ru.yandex.practicum.filmorate.exceptions.filmsExceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.likesExceptions.IllegalNumberFilmsCountException;

@RestControllerAdvice
public class FilmsHandlerException {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse handlerFilmNotFoundException(final FilmNotFoundException exception) {
		return new ErrorResponse(exception.getErrorMessage(), exception.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ErrorResponse handleFilmAllreadyExistException(final FilmAllreadyExistException exception) {
		return new ErrorResponse(exception.getErrorMessage(), exception.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ErrorResponse handleIllegalNumberFilmsCountException(final IllegalNumberFilmsCountException exception) {
		return new ErrorResponse(exception.getErrorMessage(), exception.getMessage());
	}
}
