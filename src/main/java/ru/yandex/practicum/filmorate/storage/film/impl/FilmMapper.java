package ru.yandex.practicum.filmorate.storage.film.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.film.GenreConverter;
import ru.yandex.practicum.filmorate.model.film.Mpa;
import ru.yandex.practicum.filmorate.model.film.MpaConverter;

@Component
public class FilmMapper implements RowMapper<Film> {

	MpaConverter mpaConverter;
	GenreConverter genreConverter;

	public FilmMapper(MpaConverter mpaConverter, GenreConverter genreConverter) {
		this.mpaConverter = mpaConverter;
		this.genreConverter = genreConverter;
	}

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