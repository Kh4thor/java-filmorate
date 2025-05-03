package ru.yandex.practicum.filmorate.model.user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthdayValidator.class)
public @interface Birthday {

	Class<?>[] groups() default {};

	String message() default "Пользователь не может быть младше 14 лет.";

	Class<? extends Payload>[] payload() default {};
}
