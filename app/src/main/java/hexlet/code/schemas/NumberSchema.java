package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    private boolean isPositive = false;
    private Integer rangeStart = null;
    private Integer rangeEnd = null;

    public NumberSchema() {
        addCheck(value -> value instanceof Integer);
    }

    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        this.isPositive = true;
        addCheck(value -> (Integer) value > 0);
        return this;
    }

    public NumberSchema range(int start, int end) {
        this.rangeStart = start;
        this.rangeEnd = end;
        addCheck(value -> {
            int num = (Integer) value;
            return num >= start && num <= end;
        });
        return this;
    }
}
