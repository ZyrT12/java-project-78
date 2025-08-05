package hexlet.code.schemas;

import java.util.Objects;

/**
 * Schema for string values validation.
 */
public final class StringSchema extends BaseSchema<String> {
    /**
     * Sets the field as required (non-null and non-empty).
     * @return Current schema instance
     */
    @Override
    public StringSchema required() {
        addCheck(Objects::nonNull);
        addCheck(value -> !value.isEmpty());
        return this;
    }

    /**
     * Sets minimum string length requirement.
     * @param length Minimum allowed length
     * @return Current schema instance
     */
    public StringSchema minLength(int length) {
        addCheck(value -> value.length() >= length);
        return this;
    }

    /**
     * Sets required substring presence.
     * @param substring Substring that must be present
     * @return Current schema instance
     */
    public StringSchema contains(String substring) {
        addCheck(value -> value.contains(substring));
        return this;
    }

    @Override
    protected boolean isInstance(Object value) {
        return value == null || value instanceof String;
    }
}
