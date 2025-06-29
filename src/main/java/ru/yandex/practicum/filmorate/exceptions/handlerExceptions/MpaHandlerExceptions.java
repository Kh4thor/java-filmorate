package ru.yandex.practicum.filmorate.exceptions.handlerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import ru.yandex.practicum.filmorate.exceptions.ErrorResponse;
import ru.yandex.practicum.filmorate.exceptions.mpaExceptions.MpaIsOutOfRangeException;

public class MpaHandlerExceptions {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse handlerMpaIsOutOfRangeException(final MpaIsOutOfRangeException exception) {
		return new ErrorResponse(exception.getErrorMessage(), exception.getMessage());
	}

}
