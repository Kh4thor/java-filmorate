package ru.yandex.practicum.filmorate.model.user;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
	@NotBlank(message = "Поле email не может содержать пробелы")
	@NotNull(message = "Поле email не может быть null")
	private String email;

	// день рождения пользователя
	@Past
	@Birthday(message = "Пользователь не может быть младше 14 лет.")
	@NotNull(message = "Поле birhday не может быть null")
	private LocalDate birthday;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(email, other.email)
				&& Objects.equals(login, other.login) && Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, email, login, name);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", login=" + login + ", email=" + email + ", birthday=" + birthday
				+ "]";
	}

	@Override
	public User clone() throws CloneNotSupportedException {
		return (User) super.clone();
	}
}
