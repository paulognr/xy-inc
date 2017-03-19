package br.com.paulognr.application.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.metadata.ConstraintDescriptor;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import br.com.paulognr.application.enums.ComparisonOperator;

@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
		ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CompareToField.CompareValidator.class)
@ReportAsSingleViolation
public @interface CompareToField {

	String MESSAGE_ID = "{br.com.paulognr.application.validation.FieldComparation}";

	String message() default MESSAGE_ID;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	ComparisonOperator comparator();

	String source() default "";

	String target() default "";

	@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CompareToField[] value();
	}

	class CompareValidator implements ConstraintValidator<CompareToField, Object> {

		private String sourceFieldName;
		private String targetFieldName;
		private ComparisonOperator comparator;

		@Override
		public void initialize(CompareToField annotation) {
			sourceFieldName = annotation.source();
			targetFieldName = annotation.target();
			comparator = annotation.comparator();
		}

		@Override
		public boolean isValid(Object o, ConstraintValidatorContext context) {
			final Object source;
			final Object target;
			try {
				source = BeanUtils.getProperty(o, this.sourceFieldName);
				target = BeanUtils.getProperty(o, this.targetFieldName);

				ConstraintDescriptor<?> constraintDescriptor = ((ConstraintValidatorContextImpl) context)
						.getConstraintDescriptor();
				Map<String, Object> tempMap = constraintDescriptor.getAttributes();
				Map<String, Object> tempMap2 = new HashMap();
				tempMap2.putAll(tempMap);
				tempMap2.put("comparator", comparator);
				tempMap2.put("value", this.targetFieldName);

				Field attributes = constraintDescriptor.getClass().getDeclaredField("attributes");
				attributes.setAccessible(true);
				attributes.set(constraintDescriptor, Collections.unmodifiableMap(tempMap2));

				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
						.addPropertyNode(this.sourceFieldName).addConstraintViolation();

				if (source != null && target != null) {
					if (ComparisonOperator.EQUAL == this.comparator) {
						if (((Comparable) source).compareTo(target) != 0) {
							return false;
						}
					} else if (ComparisonOperator.LESS_THAN == this.comparator) {
						if (((Comparable) source).compareTo(target) >= 0) {
							return false;
						}
					} else if (ComparisonOperator.GREATER_THAN_OR_EQUAL == this.comparator) {
						if (((Comparable) source).compareTo(target) < 0) {
							return false;
						}
					}
				}
			} catch (final Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}

			return true;
		}
	}
}