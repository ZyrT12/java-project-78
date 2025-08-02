package hexlet.code.schemas;

/**
 * Schema for numeric values validation.
 */
public final class NumberSchema extends BaseSchema<Integer> {
    private boolean positive = false;
    private Integer rangeStart = null;
    private Integer rangeEnd = null;

    public NumberSchema() {
        addCheck(value -> true);
    }

    /**
     * Sets the field as required (non-null).
     * @return Current schema instance
     */
    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    /**
     * Validates that number is positive (> 0).
     * @return Current schema instance
     */
    public NumberSchema positive() {
        this.positive = true;
        addCheck(value -> value > 0);
        return this;
    }

    /**
     * Sets allowed value range (inclusive).
     * @param start Range start (inclusive)
     * @param end Range end (inclusive)
     * @return Current schema instance
     */
    public NumberSchema range(int start, int end) {
        this.rangeStart = start;
        this.rangeEnd = end;
        addCheck(value -> value >= start && value <= end);
        return this;
    }
}
