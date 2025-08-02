package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    public StringSchema required() {
        setRequired(true);
        addCheck("required", value ->
                value instanceof String && !((String) value).isEmpty()
        );
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", value ->
                value instanceof String && ((String) value).length() >= length
        );
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", value ->
                value instanceof String && ((String) value).contains(substring)
        );
        return this;
    }
}
