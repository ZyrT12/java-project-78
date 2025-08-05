package hexlet.code.schemas;

import java.util.function.Predicate;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * Base schema class for validation.
 * @param <T> Type of value to validate
 */
public abstract class BaseSchema<T> {
    private boolean required;
    private final List<Predicate<T>> checks = new ArrayList<>();

    /**
     * Validates the value against all checks.
     * @param value Value to validate
     * @return true if valid, false otherwise
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
     * Makes the field required.
     * @return Current schema instance
     */
    public BaseSchema<T> required() {
        this.required = true;
        addCheck(Objects::nonNull);
        return this;
    }

    protected final void addCheck(Predicate<T> check) {
        checks.add(check);
    }

    protected abstract boolean isInstance(Object value);
}
