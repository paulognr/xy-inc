package br.com.paulognr.application.annotations;

import java.lang.reflect.Field;

import javax.validation.ConstraintViolation;

import com.google.gson.JsonObject;

public class AnnotationUtil {
	public static JsonObject getValue(ConstraintViolation v, JsonObject violationDetails)
			throws NoSuchFieldException, IllegalAccessException {
		Field declaredField = v.getInvalidValue().getClass().getDeclaredField(v.getPropertyPath().toString());
		declaredField.setAccessible(true);
		violationDetails.addProperty("value", declaredField.get(v.getInvalidValue()).toString());
		return violationDetails;
	}
}