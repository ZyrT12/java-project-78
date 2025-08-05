package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;

/**
 * Schema for validating maps.
 */
public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<?>> shapeSchemas;
    private Integer requiredSize;

    @Override
    protected boolean isInstance(Object value) {
        return value instanceof Map;
    }

    /**
     * Sets required size for the map.
     * @param mapSize Required size
     * @return Current schema instance
     */
    public MapSchema sizeof(int mapSize) {
        this.requiredSize = mapSize;
        addCheck(map -> map.size() == mapSize);
        return this;
    }

    /**
     * Sets shape validation for the map.
     * @param shapeMap Map of schemas for validation
     * @return Current schema instance
     */
    public MapSchema shape(Map<String, BaseSchema<String>> shapeMap) {
        this.shapeSchemas = new HashMap<>(shapeMap);
        addCheck(this::validateShape);
        return this;
    }

    private boolean validateShape(Map<?, ?> map) {
        return shapeSchemas.entrySet().stream()
                .allMatch(entry -> {
                    Object value = map.get(entry.getKey());
                    BaseSchema<?> schema = entry.getValue();
                    return value != null && schema.isValid(value);
                });
    }
}
