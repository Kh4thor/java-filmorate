package ru.yandex.practicum.filmorate.model.film;

import org.springframework.stereotype.Component;

@Component
public class GenreConverter {

	public int converToIdByGenre(String genre) {
		return switch (genre.toString().toLowerCase().replaceAll("\\s+", "")) {
		case "comedy" -> 1;
		case "drama" -> 2;
		case "cartoon" -> 3;
		case "thriller" -> 4;
		case "documentary" -> 5;
		case "action" -> 6;
		default -> 0;
		};
	}

	public String convertToGenreById(Integer genreId) {
		return switch (genreId) {
		case 1 -> "comedy";
		case 2 -> "drama";
		case 3 -> "cartoon";
		case 4 -> "thriller";
		case 5 -> "documentary";
		case 6 -> "action";
		default -> "undefined";
		};
	}
}
