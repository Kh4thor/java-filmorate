package ru.yandex.practicum.filmorate.mvc.storage.film.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.genre.Genre;
import ru.yandex.practicum.filmorate.mvc.storage.film.FilmAppStorage;
import ru.yandex.practicum.filmorate.utills.mappers.FilmRowMapper;

@Repository("dbFilmStorage")
public class DbFilmStorage implements FilmAppStorage<Film> {

	private final JdbcTemplate jdbcTemplate;

	public DbFilmStorage(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/*
	 * добавть фильм
	 */
	@Override
	public Film addFilm(Film film) {
		// добавление фильма в таблицу films

		Integer mpaId = null;
		String addFilmSql = "INSERT INTO films (name, description, release, duration, mpa) VALUES (?, ?, ?, ?, ?)";
		if (film.getMpa() != null) {
			mpaId = film.getMpa().getId();
		}
		jdbcTemplate.update(addFilmSql, film.getName(), film.getDescription(), film.getReleaseDate(),
				film.getDuration(), mpaId);
		String filmIdSql = "SELECT MAX(id) FROM films";
		Long filmId = jdbcTemplate.queryForObject(filmIdSql, Long.class);
		film.setId(filmId);
		addGenresOfFilm(film);
		return getFilm(filmId);
	}

	/*
	 * добавление жанров фильма в промежуточную таблицу films_genres
	 */
	private void addGenresOfFilm(Film film) {
		String deleteFilmsGenresSql = "DELETE FROM films_genres WHERE film_id=?";
		jdbcTemplate.update(deleteFilmsGenresSql, film.getId());

		String addGenreSql = "INSERT INTO films_genres (film_id, genre_id) VALUES (?,?)";
		List<Genre> genreIdList = film.getGenres();
		// заполнение таблицы films_genres для каждого значения id-жанра
		for (Genre element : genreIdList) {
			int genreId = element.getId();
			jdbcTemplate.update(addGenreSql, film.getId(), genreId);
		}
	}

	/*
	 * обновить фильм
	 */
	@Override
	public Film updateFilm(Film film) {
		// Обновляем основные данные о фильме
		String updateFilmSql = "UPDATE films SET name=?, description=?, release=?, duration=?, mpa=? WHERE id=?";
		Integer mpaId = null;
		if (film.getMpa() != null) {
			mpaId = film.getMpa().getId();
		}
		jdbcTemplate.update(updateFilmSql, film.getName(), film.getDescription(), film.getReleaseDate(),
				film.getDuration(), mpaId, film.getId());

		String insertGenreSql = "MERGE INTO films_genres KEY(film_id, genre_id) VALUES (?, ?)";
		for (Genre genre : film.getGenres()) {
			jdbcTemplate.update(insertGenreSql, film.getId(), genre.getId());
		}

		return getFilm(film.getId());
	}

	/*
	 * очистить хранилище
	 */
	@Override
	public void clear() {
		jdbcTemplate.update("TRUNCATE TABLE films");
	}

	/*
	 * проверить хранилище на наличие ключа-id фильма
	 */
	@Override
	public boolean isFilmExist(Long filmId) {
		String isFilmExistSql = "SELECT id FROM films WHERE id=? ";
		List<Long> filmIdList = jdbcTemplate.queryForList(isFilmExistSql, Long.class, filmId);
		return !filmIdList.isEmpty();
	}

	/*
	 * получить фильм из хранилища
	 */
	@Override
	public Film getFilm(Long filmId) {
		String getFilmSql = "SELECT f.id AS id,"
								+ " f.name AS name,"
								+ " f.description AS description,"
								+ " f.release AS release,"
								+ " f.duration AS duration,"
								+ " m.id AS mpaId,"
								+ " m.name AS mpaName,"
								+ " STRING_AGG('{ \"id\": ' || g.id || ', \"name\": \"' || g.name || '\" }', ', ') AS genres_json "
							+ "FROM films AS f "
							+ "LEFT JOIN mpa AS m ON m.id = f.mpa "
							+ "LEFT JOIN films_genres AS fg ON f.id = fg.film_id "
							+ "LEFT JOIN genres AS g ON fg.genre_id = g.id "
							+ "WHERE f.id = ? "
							+ "GROUP BY f.id, f.name, f.description, f.release, f.duration, m.id, m.name";
		return jdbcTemplate.queryForObject(getFilmSql, new FilmRowMapper(), filmId);
	}

	/*
	 * удалить фильм из хранилища
	 */
	@Override
	public Film removeFilm(Long filmId) {
		Film film = getFilm(filmId);
		// удалить фильм из таблицы films_genres
		String deleteGenreSql = "DELETE FROM films_genres WHERE film_id=? ";
		jdbcTemplate.update(deleteGenreSql, filmId);
		// удалить фильм из таблицы films
		String deleteFilmSql = "DELETE FROM films WHERE film_id=? ";
		jdbcTemplate.update(deleteFilmSql, filmId);
		return film;
	}

	/*
	 * получить спискок фильмов по рейтингу
	 */
	@Override
	public List<Film> getRatedFilms(List<Long> ratedFilmsIdList) {
		List<Film> filmList = new ArrayList<>();
		for (int i = 0; i < ratedFilmsIdList.size(); i++) {
			Long filmId = ratedFilmsIdList.get(i);
			Film film = getFilm(filmId);
			filmList.add(film);
		}
		return filmList;
	}

	/*
	 * получить список всех фильмов
	 */
	@Override
	public List<Film> getAllFilms() {
		List<Film> filmList = new ArrayList<>();
		String sql = "SELECT id FROM films GROUP BY id";
		// получить id-список всех фильмов
		List<Long> filmIdList = jdbcTemplate.queryForList(sql, Long.class);
		// получение и добавление фильмов в список по id-списку
		for (int i = 0; i < filmIdList.size(); i++) {
			Long filmId = filmIdList.get(i);
			Film film = getFilm(filmId);
			filmList.add(film);
		}
		return filmList;
	}
}