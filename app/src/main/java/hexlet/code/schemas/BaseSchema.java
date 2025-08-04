package hexlet.code.schemas;

import java.util.function.Predicate;
import java.util.Objects;

/**
 * Base schema class that provides common validation functionality.
 * Designed for extension by specific schema types.
 */
public abstract class BaseSchema<T> {
    private boolean isRequired;
    private Predicate<T> validation = value -> true;

    /**
     * Validates the given value against the schema rules.
     * @param value The value to validate
     * @return true if the value is valid according to the schema, false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired;
        }

        try {
            return validation.test((T) value);
        } catch (ClassCastException e) {
            return false;
        }
    }

    /**
     * Marks the field as required, meaning null values will be considered invalid.
     * @return the current schema instance for method chaining
     */
    public BaseSchema<T> required() {
        isRequired = true;
        addCheck(Objects::nonNull);
        return this;
    }

    /**
     * Adds a new validation check to the schema.
     * @param check The predicate to add as a validation check
     */
    protected final void addCheck(Predicate<T> check) {
        validation = validation.and(check);
    }

    /**
     * Gets the required status of the schema.
     * @return true if the schema is marked as required
     */
    protected boolean getIsRequired() {
        return isRequired;
    }

    /**
     * Gets the current validation predicate.
     * @return the current validation predicate
     */
    protected Predicate<T> getValidation() {
        return validation;
    }
}
