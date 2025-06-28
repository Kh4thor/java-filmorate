package ru.yandex.practicum.filmorate.storage.film.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.film.IdGenreIdentifier;
import ru.yandex.practicum.filmorate.model.film.IdMpaIdentifier;
import ru.yandex.practicum.filmorate.storage.film.FilmAppStorage;

@Repository
@Component
public class DbFilmStorage implements FilmAppStorage<Film> {

	private final JdbcTemplate jdbcTemplate;
	private final IdMpaIdentifier idMpaIdentifier;
	private final IdGenreIdentifier idGenreIdentifier;

	public DbFilmStorage(JdbcTemplate jdbcTemplate, IdMpaIdentifier idMpaIdentifier,
			IdGenreIdentifier idGenreIdentifier) {
		this.jdbcTemplate = jdbcTemplate;
		this.idMpaIdentifier = idMpaIdentifier;
		this.idGenreIdentifier = idGenreIdentifier;
	}

	@Override
	public Film addFilm(Film film) {
		int mpaId = idMpaIdentifier.identifyMpaId(film.getMpa());
		int genreId = idGenreIdentifier.identifyGenreId(film.getGenre());
		String sql = "INSERT INTO films (id, name, description, release, duration, genre, mpa) VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, film.getId(), film.getName(), film.getDescription(), film.getReleaseDate(),
				film.getDuration(), genreId, mpaId);
		return getFilm(film.getId());
	}

	@Override
	public Film updateFilm(Film film) {
		int mpaId = idMpaIdentifier.identifyMpaId(film.getMpa());
		int genreId = idGenreIdentifier.identifyGenreId(film.getGenre());
		String sql = "UPDATE films SET name=?, description=?, release=?, duration=?, genre=?, mpa=? WHERE id=?";
		jdbcTemplate.update(sql, film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration(),
				film.getGenre(), genreId, mpaId);
		return getFilm(film.getId());
	}

	@Override
	public void clear() {
		jdbcTemplate.update("TRUNCATE TABLE films");
	}

	@Override
	public boolean isFilmExist(Long filmId) {
		String sql = "SELECT id FROM films WHERE id=? ";
		List<Long> filmIdList = jdbcTemplate.queryForList(sql, Long.class, filmId);
		return !filmIdList.isEmpty();
	}

	@Override
	public Film getFilm(Long filmId) {
		String sql = "SELECT * FROM films WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new FilmMapper());
	}

	@Override
	public Film removeFilm(Long filmId) {
		String sql = "DELETE FROM films WHERE id=?";
		jdbcTemplate.update(sql, filmId);
		return null;
	}

	@Override
	public List<Film> getRatedFilms(List<Long> ratedFilmsIdList) {
		String sql = "SELECT * FROM films WHERE id IN ("
				+ ratedFilmsIdList.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";
		return jdbcTemplate.query(sql, new FilmMapper(), ratedFilmsIdList.toArray());
	}

	@Override
	public List<Film> getAllFilms() {
		String sql = "SELECT * FROM films";
		return jdbcTemplate.query(sql, new FilmMapper());
	}

	static class FilmMapper implements RowMapper<Film> {
		@Override
		public Film mapRow(ResultSet rs, int rowNum) throws SQLException {

			Film film = new Film();
			film.setId(rs.getLong("id"));
			film.setName(rs.getString("name"));
			film.setDescription(rs.getString("description"));
			film.setReleaseDate(rs.getDate("release").toLocalDate());
			film.setDuration(rs.getLong("duration"));
			film.setGenre(rs.getString("genre"));
			film.setMpa(rs.getString("mpa"));
			return film;
		}
	}
}