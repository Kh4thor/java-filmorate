package ru.yandex.practicum.filmorate.storage.film.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.film.Genre;
import ru.yandex.practicum.filmorate.model.film.GenreConverter;
import ru.yandex.practicum.filmorate.model.film.MpaConverter;
import ru.yandex.practicum.filmorate.storage.film.FilmAppStorage;

@Repository
@Component
public class DbFilmStorage implements FilmAppStorage<Film> {

	private final JdbcTemplate jdbcTemplate;
	private final MpaConverter mpaConverter;
	private final GenreConverter genreConverter;

	public DbFilmStorage(JdbcTemplate jdbcTemplate, MpaConverter mpaConverter, GenreConverter genreConverter) {
		this.jdbcTemplate = jdbcTemplate;
		this.mpaConverter = mpaConverter;
		this.genreConverter = genreConverter;
	}

	/*
	 * добавть фильм
	 */
	@Override
	public Film addFilm(Film film) {
		String sql = "INSERT INTO films (id, name, description, release, duration, genre, mpa) VALUES (?, ?, ?, ?, ?, ?, ?)";
		List<Genre> genreIdList = film.getGenres();
		// заполнение таблицы для каждого значения id-жанра
		for (int i = 0; i < genreIdList.size(); i++) {
			int genreId = genreIdList.get(i).getId();
			jdbcTemplate.update(sql, film.getId(), film.getName(), film.getDescription(), film.getReleaseDate(),
					film.getDuration(), genreId, film.getMpa().getId());
		}
		return getFilm(film.getId());
	}

	/*
	 * обновить фильм
	 */
	@Override
	public Film updateFilm(Film film) {
		removeFilm(film.getId());
		return addFilm(film);
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
		String sql = "SELECT id FROM films WHERE id=? ";
		List<Long> filmIdList = jdbcTemplate.queryForList(sql, Long.class, filmId);
		return !filmIdList.isEmpty();
	}

	/*
	 * получить фильм из хранилища
	 */
	@Override
	public Film getFilm(Long filmId) {
		// получение фильм
		String sqlFilm = "SELECT id, name, description, release, duration, mpa FROM films WHERE id=? GROUP BY id";
		Film film = jdbcTemplate.queryForObject(sqlFilm, new FilmMapper(mpaConverter, genreConverter), filmId);
		// получение id-списка жанров полученного фильма
		String sqlGenreList = "SELECT genre FROM films WHERE id=?";
		List<Genre> genreList = new ArrayList<>();
		List<Integer> idList = jdbcTemplate.queryForList(sqlGenreList, Integer.class, filmId);
		// заполнение списка жанров <Genre> genres значениями из таблицы
		for (int i = 0; i < idList.size(); i++) {
			int genreId = idList.get(i);
			genreList.add(new Genre(genreId));
		}
		// добоваление полученного id-списка жанров к полученному фильму
		film.setGenres(genreList);
		return film;
	}

	/*
	 * удалить фильм из хранилища
	 */
	@Override
	public Film removeFilm(Long filmId) {
		Film film = getFilm(filmId);
		String sql = "DELETE FROM films WHERE id=? ";
		jdbcTemplate.update(sql, filmId);
		return film;
	}

	/*
	 * получить спискок фильмов по рейтингу
	 */
	@Override
	public List<Film> getRatedFilms(List<Long> ratedFilmsIdList) {
		String sql = "SELECT * FROM films WHERE id IN ("
				+ ratedFilmsIdList.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";
		return jdbcTemplate.query(sql, new FilmMapper(mpaConverter, genreConverter), ratedFilmsIdList.toArray());
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