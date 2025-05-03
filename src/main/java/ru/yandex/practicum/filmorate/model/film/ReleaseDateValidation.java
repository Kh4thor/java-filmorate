package ru.yandex.practicum.filmorate.model.film;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReleaseDateValidator.class)
public @interface ReleaseDateValidation {

	Class<?>[] groups() default {};

	String message() default "Дата релиза не может быть раньше 28 декабря 1895 года";

	Class<? extends Payload>[] payload() default {};
}