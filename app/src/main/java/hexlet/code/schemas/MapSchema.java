package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<?>> schemas;
    private boolean required = false;
    private Integer size;

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;
        }

        if (!(value instanceof Map)) {
            return false;
        }

        Map<?, ?> map = (Map<?, ?>) value;

        if (size != null && map.size() != size) {
            return false;
        }

        if (schemas != null) {
            return validateShape(map);
        }

        return true;
    }

    private boolean validateShape(Map<?, ?> map) {
        return schemas.entrySet().stream()
                .allMatch(entry -> {
                    String key = entry.getKey();
                    BaseSchema<?> schema = entry.getValue();
                    Object value = map.get(key);

                    // Специальная обработка для String схем
                    if (schema instanceof StringSchema) {
                        return value != null && ((StringSchema) schema).isValid(value.toString());
                    }
                    return value != null && schema.isValid(value);
                });
    }

    public MapSchema required() {
        this.required = true;
        return this;
    }

    public MapSchema sizeof(int newSize) {
        this.size = newSize;
        return this;
    }


    public <T> MapSchema shape(Map<String, BaseSchema<T>> shapeSchemas) {
        this.schemas = new HashMap<>(shapeSchemas);
        return this;
    }
}
