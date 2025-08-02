package hexlet.code.schemas;

import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseSchema {
    private boolean isRequired = false;
    private final List<Predicate<Object>> checks = new ArrayList<>();

    protected final void addCheck(Predicate<Object> check) {
        checks.add(check);
    }

    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired;
        }

        return checks.stream().allMatch(check -> check.test(value));
    }

    public BaseSchema required() {
        this.isRequired = true;
        return this;
    }
}
