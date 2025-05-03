package ru.yandex.practicum.filmorate.model.user;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@RestController
@Name(message = "Имя не было задано. В качестве имени используется логин.")
public class User {

	// id пользователя
	private Long id;

	// имя пользователя
	private String name;

	// login пользователя
	@NotBlank(message = "Поле логин login не может состоять из пробелов")
	@NotNull(message = "Поле login не может быть null")
	private String login;

	// email пользователя
	@Email(message = "Поле email должно содежать символ @")
	@UniqueEmail(message = "Пользователь с таким email уже зарегестрирован")
	@NotBlank(message = "Поле email не может содержать пробелы")
	@NotNull(message = "Поле email не может быть null")
	private String email;

	// день рождения пользователя
	@Past
	@Birthday(message = "Пользователь не может быть младше 14 лет.")
	@NotNull(message = "Поле birhday не может быть null")
	private LocalDate birthday;

	@Override
	public User clone() throws CloneNotSupportedException {
		return (User) super.clone();
	}
}
