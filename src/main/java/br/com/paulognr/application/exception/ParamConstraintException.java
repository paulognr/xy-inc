package br.com.paulognr.application.exception;

import java.lang.invoke.MethodHandles;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;

import org.jboss.logging.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.paulognr.application.annotations.AnnotationUtil;
import br.com.paulognr.application.annotations.CompareToField;

public class ParamConstraintException extends BaseException {

	private static final long serialVersionUID = 1L;

	public static final ErrorCode INVALID_PARAMS = new ErrorCode("XY_CONSTRAINT_0001",
			"Parameters constraints violated.");

	private final Set<? extends ConstraintViolation<?>> constraintViolations;
	private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass());

	public ParamConstraintException(ErrorCode error, String details,
			Set<? extends ConstraintViolation<?>> constraintViolations) {
		this(error, details, null, constraintViolations);
	}

	public ParamConstraintException(ErrorCode error, String details, Throwable cause,
			Set<? extends ConstraintViolation<?>> constraintViolations) {
		super(error.getCode(), error.getDescription(), details, cause);
		this.constraintViolations = constraintViolations;
	}

	public ParamConstraintException(ErrorCode error, Throwable cause,
			Set<? extends ConstraintViolation<?>> constraintViolations) {
		this(error, null, cause, constraintViolations);
	}

	public ParamConstraintException(ErrorCode error, Set<? extends ConstraintViolation<?>> constraintViolations) {
		this(error, null, null, constraintViolations);
	}

	@Override
	public JsonObject toJson() {
		JsonObject jsonObject = super.toJson();

		JsonArray violationsArray = new JsonArray();
		constraintViolations.forEach(v -> {
			JsonObject violationDetails = new JsonObject();

			violationDetails.addProperty("field", v.getPropertyPath().toString());
			violationDetails.addProperty("value",
					v.getInvalidValue() != null ? v.getInvalidValue().toString() : "null");
			violationDetails.addProperty("messageId", v.getMessageTemplate().replaceAll("^\\{|\\}$", ""));

			Map<String, Object> attributes = v.getConstraintDescriptor().getAttributes();
			JsonArray expectedValues = new JsonArray();
			attributes.forEach((key, value) -> {
				Stream<String> keyStream = Stream.of("value", "min", "max", "comparator");
				boolean anyMatch = keyStream.anyMatch(key::equals);

				if (anyMatch) {
					expectedValues.add(attributes.get(key).toString());
				}
			});

			if (v.getMessageTemplate().equals(CompareToField.MESSAGE_ID)) {
				try {
					violationDetails = AnnotationUtil.getValue(v, violationDetails);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					logger.error("Error getting value from violation details!", e);
				}
			}

			if (expectedValues.size() > 0) {
				violationDetails.add("expected", expectedValues);
			}
			violationsArray.add(violationDetails);
		});
		jsonObject.add("violations", violationsArray);

		return jsonObject;
	}

	public Set<? extends ConstraintViolation<?>> getConstraintViolations() {
		return constraintViolations;
	}
}