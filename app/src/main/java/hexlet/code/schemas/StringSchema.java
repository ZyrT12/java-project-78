package hexlet.code.schemas;

/**
 * Schema for string values validation.
 */
public final class StringSchema extends BaseSchema<String> {
    private int minLength = 0;
    private String requiredSubstring = "";

    public StringSchema() {
        addCheck(value -> true);
    }

    /**
     * Sets the field as required (non-null and non-empty).
     * @return Current schema instance
     */
    @Override
    public StringSchema required() {
        super.required();
        addCheck(value -> !value.isEmpty());
        return this;
    }

    /**
     * Sets minimum string length requirement.
     * @param length Minimum allowed length
     * @return Current schema instance
     */
    public StringSchema minLength(int length) {
        this.minLength = length;
        addCheck(value -> value.length() >= minLength);
        return this;
    }

    /**
     * Sets required substring presence.
     * @param substring Substring that must be present
     * @return Current schema instance
     */
    public StringSchema contains(String substring) {
        this.requiredSubstring = substring;
        addCheck(value -> value.contains(substring));
        return this;
    }
}
