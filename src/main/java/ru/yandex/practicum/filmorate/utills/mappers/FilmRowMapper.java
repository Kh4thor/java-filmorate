package ru.yandex.practicum.filmorate.utills.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.film.Mpa;

public class FilmRowMapper implements RowMapper<Film> {

	@Override
	public Film mapRow(ResultSet rs, int rowNum) throws SQLException {

		Film film = new Film();
		film.setId(rs.getLong("id"));
		film.setName(rs.getString("name"));
		film.setDescription(rs.getString("description"));
		film.setReleaseDate(rs.getDate("release").toLocalDate());
		film.setDuration(rs.getLong("duration"));
		film.setMpa(new Mpa(rs.getInt("mpa")));
		return film;
	}
}