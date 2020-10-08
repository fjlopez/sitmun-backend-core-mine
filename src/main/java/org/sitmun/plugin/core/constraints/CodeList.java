package org.sitmun.plugin.core.constraints;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {CodeListValidator.class})
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeList {

  /**
   * Name of the code list.
   */
  String value();

  /**
   * Required for a Constraint annotation.
   */
  String message() default "Invalid value";

  /**
   * Required for a Constraint annotation.
   */
  Class<?>[] groups() default {};

  /**
   * Required for a Constraint annotation.
   */
  Class<? extends Payload>[] payload() default {};
}
