package ru.yandex.practicum.filmorate.exception;

import java.io.IOException;

public class HandlerException extends IOException {

	private static final long serialVersionUID = 8255655802692976455L;

	public HandlerException(String string) {
		super(string);
	}
}
