package ru.yandex.practicum.filmorate.model.film;

import org.springframework.stereotype.Component;

@Component
public class IdGenreIdentifier {

	public int identifyGenreId(String genre) {
		return switch (genre.toLowerCase()) {
		case "комедия" -> 1;
		case "драма" -> 2;
		case "мультфильм" -> 3;
		case "триллер" -> 4;
		case "документальный" -> 5;
		case "боевик" -> 6;
		default -> 0;
		};
	}
}
