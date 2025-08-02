package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        setRequired(true);
        addCheck("required", value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value ->
                value == null || (value instanceof Integer && (Integer) value > 0)
        );
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", value ->
                value instanceof Integer && (Integer) value >= min && (Integer) value <= max
        );
        return this;
    }
}
