package hexlet.code.schemas;

/**
 * Validation schema for string values.
 */
public final class StringSchema extends BaseSchema<String> {

    /**
     * Default constructor with initial no-op check.
     */
    public StringSchema() {
        addCheck(v -> true);
    }

    /**
     * Marks the string as required (non-null and non-empty).
     *
     * @return the current schema instance
     */
    @Override
    public StringSchema required() {
        super.required();  
        addCheck(value -> value != null && !value.isEmpty()); // Плюс пустая строка недопустима
        return this;
    }

    /**
     * Adds a rule that the string must have at least the given length.
     *
     * @param length minimum length required
     * @return the current schema instance
     */
    public StringSchema minLength(int length) {
        addCheck(value -> value == null || value.length() >= length);
        return this;
    }

    /**
     * Adds a rule that the string must contain the specified substring.
     *
     * @param substring the substring that must be present
     * @return the current schema instance
     */
    public StringSchema contains(String substring) {
        addCheck(value -> value == null || value.contains(substring));
        return this;
    }
}
