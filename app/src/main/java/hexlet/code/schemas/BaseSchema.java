package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Base schema class for validation.
 * <p>
 * This class is designed to be extended by specific schema implementations.
 * When extending, follow these guidelines:
 * <ul>
 *   <li>Override {@link #isValid(Object)} only if necessary</li>
 *   <li>Call super.isValid(value) first to preserve base validation</li>
 *   <li>Add custom validation logic after base validation</li>
 * </ul>
 * </p>
 */
public abstract class BaseSchema {
    private final Map<String, Predicate<Object>> checks = new HashMap<>();
    private boolean required = false;

    protected final void addCheck(String name, Predicate<Object> validate) {
        checks.put(name, validate);
    }

    protected final void setRequired(boolean isRequired) {
        this.required = isRequired;
    }

    protected final boolean isRequired() {
        return required;
    }

    /**
     * Validates the value against schema rules.
     * <p>
     * When extending this method:
     * 1. First perform custom validation
     * 2. Call super.isValid(value)
     * 3. Return combined result
     * </p>
     * @param value Value to validate
     * @return true if valid, false otherwise
     */
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }

        for (Predicate<Object> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
