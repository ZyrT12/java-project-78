package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;

/**
 * Schema for Map objects validation.
 */
public final class MapSchema extends BaseSchema<Map<String, Object>> {
    private int requiredSize = -1;
    private Map<String, BaseSchema<?>> shapeRules = new HashMap<>();

    public MapSchema() {
        addCheck(value -> value == null || value instanceof Map);
    }

    /**
     * Sets the field as required (non-null Map).
     * @return Current schema instance
     */
    @Override
    public MapSchema required() {
        super.required();
        addCheck(value -> value instanceof Map);
        return this;
    }

    /**
     * Sets exact size requirement for the Map.
     * @param size Expected number of key-value pairs
     * @return Current schema instance
     */
    public MapSchema sizeof(int size) {
        this.requiredSize = size;
        addCheck(value -> requiredSize < 0 || value.size() == requiredSize);
        return this;
    }

    /**
     * Sets validation rules for Map values.
     * @param schemas Map of validation schemas for each key
     * @return Current schema instance
     */
    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        this.shapeRules = new HashMap<>(schemas);
        addCheck(this::validateShape);
        return this;
    }

    private boolean validateShape(Map<String, Object> map) {
        return shapeRules.entrySet().stream()
                .allMatch(entry -> {
                    Object value = map.get(entry.getKey());
                    return entry.getValue().isValid(value);
                });
    }
}
