package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.Objects;

/**
 * Base class for all validation schemas.
 *
 * @param <T> the type of data this schema validates
 */
public abstract class BaseSchema<T> {
    private boolean required;
    private final List<Predicate<T>> checks = new ArrayList<>();

    /**
     * Validates the given value against all registered checks.
     *
     * @param value the value to validate
     * @return true if the value is valid, false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }

        try {
            T typedValue = (T) value;
            return checks.stream().allMatch(p -> p.test(typedValue));
        } catch (ClassCastException e) {
            return false;
        }
    }

    /**
     * Marks this schema as required (non-null).
     *
     * @return the current schema instance
     */
    public BaseSchema<T> required() {
        required = true;
        addCheck(Objects::nonNull);
        return this;
    }

    /**
     * Adds a new validation rule.
     *
     * @param check the predicate to validate the value
     */
    protected final void addCheck(Predicate<T> check) {
        checks.add(check);
    }

    /**
     * Checks if the field is marked as required.
     *
     * @return true if required, false otherwise
     */
    protected final boolean isRequired() {
        return required;
    }
}
