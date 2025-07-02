package ru.yandex.practicum.filmorate.utills.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.model.mpa.Mpa;

@Component
public class MpaRowMapper implements RowMapper<Mpa> {

	@Override
	public Mpa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Mpa mpa = new Mpa();
		mpa.setId(rs.getInt("id"));
		mpa.setName(rs.getString("name"));
		return mpa;
	}
}
