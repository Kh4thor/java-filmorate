package ru.yandex.practicum.filmorate.model.user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/*
* Аннотация @UniqueEmail связана с email-списком класса UserStorageService
*/
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {

	String message()

	default "Пользователь с таким email уже зарегестрирован";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
