package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<?>> schemas;
    private Integer size;

    @Override
    protected boolean isInstance(Object value) {
        return value instanceof Map;
    }

    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        this.size = size;
        addCheck(map -> map.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        this.schemas = new HashMap<>(schemas);
        addCheck(this::validateShape);
        return this;
    }

    private boolean validateShape(Map<?, ?> map) {
        return schemas.entrySet().stream()
                .allMatch(entry -> {
                    String key = entry.getKey();
                    BaseSchema<?> schema = entry.getValue();
                    Object value = map.get(key);
                    return value != null && schema.isValid(value);
                });
    }
}
