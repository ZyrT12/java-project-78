package hexlet.code.schemas;

import java.util.function.Predicate;
import java.util.Objects;

public abstract class BaseSchema<T> {
    private boolean required;
    private Predicate<T> validation = value -> true;

    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }

        if (!isInstance(value)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        T castedValue = (T) value;
        return validation.test(castedValue);
    }

    public BaseSchema<T> required() {
        this.required = true;
        addCheck(Objects::nonNull);
        return this;
    }

    protected final void addCheck(Predicate<T> check) {
        validation = validation.and(check);
    }

    protected abstract boolean isInstance(Object value);
}
