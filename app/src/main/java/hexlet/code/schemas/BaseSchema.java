package hexlet.code.schemas;

import java.util.function.Predicate;
import java.util.Objects;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;
    protected Predicate<T> validation = value -> true;

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

    public BaseSchema<T> required() {
        isRequired = true;
        addCheck(Objects::nonNull);
        return this;
    }

    protected final void addCheck(Predicate<T> check) {
        validation = validation.and(check);
    }
}
