package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
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

        Map<?, ?> mapValue = (Map<?, ?>) value;

        if (size != null && mapValue.size() != size) {
            return false;
        }

        if (schemas != null) {
            return validateShape(mapValue);
        }

        return true;
    }

    private boolean validateShape(Map<?, ?> map) {
        for (Map.Entry<String, BaseSchema<?>> entry : schemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema<?> schema = entry.getValue();

            if (!map.containsKey(key) || !schema.isValid(map.get(key))) {
                return false;
            }
        }
        return true;
    }

    public MapSchema required() {
        this.required = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        this.size = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        this.schemas = schemas;
        return this;
    }
}
