package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;

/**
 * Schema for validating Map objects.
 * Supports size validation and shape validation.
 */
public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<?>> schemas;
    private Integer sizeRequirement;

    @Override
    protected boolean isInstance(Object value) {
        return value instanceof Map;
    }

    /**
     * Sets required size for the map.
     * @param size Required map size
     * @return Current schema instance for method chaining
     */
    public MapSchema sizeof(int size) {
        this.sizeRequirement = size;
        addCheck(map -> map.size() == size);
        return this;
    }

    /**
     * Sets validation schema for map values by key.
     * @param schemas Map of schemas for validation
     * @return Current schema instance for method chaining
     */
    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        this.schemas = new HashMap<>(schemas);
        addCheck(this::validateShape);
        return this;
    }

    private boolean validateShape(Map<?, ?> map) {
        return schemas.entrySet().stream()
                .allMatch(entry -> {
                    Object value = map.get(entry.getKey());
                    BaseSchema<?> schema = entry.getValue();
                    return value != null && schema.isValid(value);
                });
    }
}
