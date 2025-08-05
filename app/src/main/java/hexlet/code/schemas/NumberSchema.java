package hexlet.code.schemas;

/**
 * Schema for validating numeric values.
 * Supports positive numbers and range validation.
 */
public final class NumberSchema extends BaseSchema<Integer> {
    @Override
    protected boolean isInstance(Object value) {
        return value == null || value instanceof Integer;
    }

    /**
     * Validates that number is positive (> 0).
     * @return Current schema instance for method chaining
     */
    public NumberSchema positive() {
        addCheck(value -> value == null || value > 0);
        return this;
    }

    /**
     * Sets allowed value range (inclusive).
     * @param min Minimum allowed value (inclusive)
     * @param max Maximum allowed value (inclusive)
     * @return Current schema instance for method chaining
     */
    public NumberSchema range(int min, int max) {
        addCheck(value -> value == null || (value >= min && value <= max));
        return this;
    }
}
