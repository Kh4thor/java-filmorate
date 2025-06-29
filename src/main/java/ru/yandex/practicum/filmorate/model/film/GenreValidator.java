package ru.yandex.practicum.filmorate.model.film;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MpaValidation.class)
public @interface GenreValidator {

	Class<?>[] groups() default {};

	String message() default "Значение mpa должно быть в диапазоне 0-6";

	Class<? extends Payload>[] payload() default {};
}