package ms.spring.crudbasic.api.infrastructure.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfirmedEqualsFieldsValidator.class)
@Documented
public @interface ConfirmedEqualsFields {
    String message() default "Este campo não corresponde ao campo '{originalField}'";
    String originalField();

    String confirmationField();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        ConfirmedEqualsFields[] value();
    }
}
