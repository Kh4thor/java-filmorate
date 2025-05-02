package ru.yandex.practicum.filmorate.model.user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.storage.impl.UsersStorageService;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@interface UniqueEmail {

	String message()

	default "Пользователь с таким email уже зарегестрирован";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

/*
 * Аннотация @UniqueEmail связана с email-списком класса UserStorageService
 */
@Slf4j
class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	UsersStorageService userStorageService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (userStorageService.getEmailList().contains(value)) {
			log.warn("Пользователь с таким email уже зарегестрирован");
			return false;
		} else {
			return true;
		}
	}
}