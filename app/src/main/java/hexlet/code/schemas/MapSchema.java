package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;

public final class MapSchema extends BaseSchema {
    private int requiredSize = -1; // -1 означает отсутствие проверки на размер
    private Map<String, BaseSchema> shapeSchemas;

    public MapSchema() {
        addCheck(value -> value == null || value instanceof Map);
    }

    @Override
    public MapSchema required() {
        super.required();
        addCheck(value -> value instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        this.requiredSize = size;
        addCheck(value -> requiredSize < 0 ||
                (value instanceof Map && ((Map<?, ?>) value).size() == requiredSize));
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.shapeSchemas = schemas;
        addCheck(value -> {
            if (!(value instanceof Map)) return false;
            Map<?, ?> map = (Map<?, ?>) value;
            return shapeSchemas.entrySet().stream()
                    .allMatch(entry -> {
                        Object val = map.get(entry.getKey());
                        return entry.getValue().isValid(val);
                    });
        });
        return this;
    }
}
