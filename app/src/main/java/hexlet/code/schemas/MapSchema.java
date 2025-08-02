package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    public MapSchema required() {
        setRequired(true);
        addCheck("required", value -> value instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value ->
                value instanceof Map && ((Map<?, ?>) value).size() == size
        );
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck("shape", value -> {
            if (!(value instanceof Map)) {
                return false;
            }

            Map<?, ?> map = (Map<?, ?>) value;
            return schemas.entrySet().stream().allMatch(entry -> {
                String key = entry.getKey();
                BaseSchema schema = entry.getValue();
                Object mapValue = map.get(key);
                return schema.isValid(mapValue);
            });
        });
        return this;
    }
}
