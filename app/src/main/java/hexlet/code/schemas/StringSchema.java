package hexlet.code.schemas;

import java.util.Objects;

/**
 * Schema for validating string values.
 * Supports required, min length and substring checks.
 */
public final class StringSchema extends BaseSchema<String> {
    private boolean isRequired = false;

    @Override
    protected boolean isInstance(Object value) {
        return value == null || value instanceof String;
    }

    /**
     * Makes the string required (non-null and non-empty).
     * @return Current schema instance for method chaining
     */
    @Override
    public StringSchema required() {
        this.isRequired = true;
        addCheck(Objects::nonNull);
        addCheck(value -> !((String) value).isEmpty());
        return this;
    }

    /**
     * Sets minimum string length requirement.
     * @param length Minimum allowed length
     * @return Current schema instance for method chaining
     */
    public StringSchema minLength(int length) {
        addCheck(value -> !isRequired || value == null || ((String) value).length() >= length);
        return this;
    }

    /**
     * Sets required substring presence.
     * @param substring Substring that must be present
     * @return Current schema instance for method chaining
     */
    public StringSchema contains(String substring) {
        addCheck(value -> !isRequired || value == null || ((String) value).contains(substring));
        return this;
    }
}
