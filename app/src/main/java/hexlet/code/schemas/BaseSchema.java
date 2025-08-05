package hexlet.code.schemas;

import java.util.function.Predicate;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * Base validation schema for all data types.
 * Provides common validation functionality.
 * @param <T> Type of values to validate
 */
public abstract class BaseSchema<T> {
    private final List<Predicate<T>> checks = new ArrayList<>();
    private boolean required = false;

    /**
     * Validates the value against all defined checks.
     * @param value Value to validate (can be null)
     * @return true if value is valid, false otherwise
     */
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }

        if (!isInstance(value)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        T castedValue = (T) value;
        return checks.stream().allMatch(check -> check.test(castedValue));
    }

    /**
     * Makes the field required (non-null).
     * @return Current schema instance for method chaining
     */
    public BaseSchema<T> required() {
        this.required = true;
        addCheck(Objects::nonNull);
        return this;
    }

    /**
     * Adds a new validation check.
     * @param check Predicate to validate values
     */
    protected final void addCheck(Predicate<T> check) {
        checks.add(check);
    }

    /**
     * Checks if value is of correct type for this schema.
     * @param value Value to check
     * @return true if value has correct type
     */
    protected abstract boolean isInstance(Object value);
}
