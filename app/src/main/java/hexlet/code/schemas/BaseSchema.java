package hexlet.code.schemas;

import java.util.function.Predicate;
import java.util.Objects;

/**
 * Base abstract class for all validation schemas.
 * @param <T> Type of values this schema can validate
 */
public abstract class BaseSchema<T> {
    private boolean isRequired = false;
    private Predicate<T> validation = value -> true;

    /**
     * Checks if the value matches the validation schema.
     * @param value Value to validate (can be null)
     * @return true if value is valid, false otherwise
     */
    public final boolean isValid(Object value) {
        if (value == null) {
            return !isRequired;
        }

        try {
            @SuppressWarnings("unchecked")
            T castedValue = (T) value;
            return validation.test(castedValue);
        } catch (ClassCastException e) {
            return false;
        }
    }

    /**
     * Marks the field as required (non-null).
     * @return Current schema instance for method chaining
     */
    public BaseSchema<T> required() {
        isRequired = true;
        addCheck(Objects::nonNull);
        return this;
    }

    /**
     * Adds a custom validation rule.
     * @param check Predicate to validate the value
     */
    protected final void addCheck(Predicate<T> check) {
        validation = validation.and(check);
    }
}
