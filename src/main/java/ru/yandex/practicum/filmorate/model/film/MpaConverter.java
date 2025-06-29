package ru.yandex.practicum.filmorate.model.film;

import org.springframework.stereotype.Component;

@Component
public class MpaConverter {

	public int convertToIdByMpa(String mpa) {
		return switch (mpa.toUpperCase().replaceAll("\\s+", "")) {
		case "G" -> 1;
		case "PG" -> 2;
		case "PG-13" -> 3;
		case "PG13" -> 3;
		case "R" -> 4;
		case "NC-17" -> 5;
		case "NC17" -> 5;
		default -> 0;
		};
	}

	public String convertToMpaById(Integer mpaId) {
		return switch (mpaId) {
		case 1 -> "G";
		case 2 -> "PG";
		case 3 -> "PG-13";
		case 4 -> "R";
		case 5 -> "NC-17";
		case 0 -> "undefined";
		default -> "undefined";
		};
	}
}
