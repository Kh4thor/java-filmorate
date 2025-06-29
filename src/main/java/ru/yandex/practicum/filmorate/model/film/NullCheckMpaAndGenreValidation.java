package ru.yandex.practicum.filmorate.model.film;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NullCheckMpaAndGenreValidator.class)
public @interface NullCheckMpaAndGenreValidation {

	Class<?>[] groups() default {};

	String message() default "Поле не может быть null";

	Class<? extends Payload>[] payload() default {};
}