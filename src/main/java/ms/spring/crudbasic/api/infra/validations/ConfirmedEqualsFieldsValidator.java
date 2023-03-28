package ms.spring.crudbasic.api.infra.validations;

import jakarta.annotation.Nullable;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class ConfirmedEqualsFieldsValidator implements ConstraintValidator<ConfirmedEqualsFields, Object> {

    private String originalField;
    private String confirmationField;
    private String message;

    private String replaceMessage(ConfirmedEqualsFields annotation) {
        return annotation.message().replace("{originalField}", annotation.originalField());
    }

    @Override
    public void initialize(final ConfirmedEqualsFields annotation) {
        this.originalField = annotation.originalField();
        this.confirmationField = annotation.confirmationField();
        this.message = replaceMessage(annotation);
    }

    @Override
    public boolean isValid(@Nullable Object value, final ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(this.originalField);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(this.confirmationField);

        boolean isValid = fieldValue != null && fieldValue.equals(fieldMatchValue);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(this.message)
                    .addPropertyNode(this.confirmationField)
                    .addConstraintViolation();
        }

        return isValid;
    }
}