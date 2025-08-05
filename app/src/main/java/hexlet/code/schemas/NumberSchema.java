package hexlet.code.schemas;

/**
 * Validation schema for numeric (Integer) values.
 */
public final class NumberSchema extends BaseSchema<Integer> {

    /**
     * Default constructor with initial no-op check.
     */
    public NumberSchema() {
        addCheck(v -> true);
    }

    /**
     * Marks the number as required (non-null).
     *
     * @return the current schema instance
     */
    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    /**
     * Adds a rule that the number must be positive (> 0).
     *
     * @return the current schema instance
     */
    public NumberSchema positive() {
        addCheck(value -> value > 0);
        return this;
    }

    /**
     * Adds a rule that the number must be within the given inclusive range.
     *
     * @param start minimum allowed value
     * @param end maximum allowed value
     * @return the current schema instance
     */
    public NumberSchema range(int start, int end) {
        addCheck(value -> value >= start && value <= end);
        return this;
    }
}
