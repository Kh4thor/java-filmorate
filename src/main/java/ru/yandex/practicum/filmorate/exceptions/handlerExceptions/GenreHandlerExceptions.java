package ru.yandex.practicum.filmorate.exceptions.handlerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.yandex.practicum.filmorate.exceptions.ErrorResponse;
import ru.yandex.practicum.filmorate.exceptions.genreExceptions.GenreValueIsOutOfRangeException;

@RestControllerAdvice
public class GenreHandlerExceptions {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse handlerGenreValueIsOutOfRangeException(final GenreValueIsOutOfRangeException exception) {
		return new ErrorResponse(exception.getErrorMessage(), exception.getMessage());
	}

}
