package ru.yandex.practicum.filmorate.model.film;

import org.springframework.stereotype.Component;

@Component
public class IdMpaIdentifier {

	public int identifyMpaId(String mpa) {
		return switch (mpa.toUpperCase()) {
		case "G" -> 1;
		case "PG" -> 2;
		case "PG-13" -> 3;
		case "R" -> 4;
		case "NC-17" -> 5;
		default -> 0;
		};
	}
}
