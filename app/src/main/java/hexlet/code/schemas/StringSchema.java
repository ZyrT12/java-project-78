package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    private int minLength = 0;
    private String requiredSubstring = "";

    public StringSchema() {
        addCheck(value -> value instanceof String);
    }

    public StringSchema required() {
        super.required();
        addCheck(value -> !((String) value).isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        addCheck(value -> ((String) value).length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        this.requiredSubstring = substring;
        addCheck(value -> ((String) value).contains(substring));
        return this;
    }
}
