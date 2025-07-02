package ru.yandex.practicum.filmorate.utills.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.model.mpa.Mpa;

public class FilmRowMapper implements RowMapper<Film> {

	@Override
	public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
		// сборка фильма
		Film film = new Film();
		film.setId(rs.getLong("id"));
		film.setName(rs.getString("name"));
		film.setDescription(rs.getString("description"));
		film.setReleaseDate(rs.getDate("release").toLocalDate());
		film.setDuration(rs.getLong("duration"));

		// сборка Mpa
		Integer mpaId = rs.getInt("mpaId");
		String mpaName = rs.getString("mpaName");
		Mpa mpa = new Mpa();
		if (mpaId != null) {
			mpa = new Mpa(mpaId, mpaName);
		}
		film.setMpa(mpa);

		// сборка List<Genre>
		String genresJson = rs.getString("genres_json");
		ObjectMapper mapper = new ObjectMapper();
		List<Genre> genres = new ArrayList<>();
		if (genresJson != null) {
			try {
				genres = mapper.readValue("[" + genresJson + "]", new TypeReference<List<Genre>>() {
				});
				film.setGenres(genres);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return film;
	}
}