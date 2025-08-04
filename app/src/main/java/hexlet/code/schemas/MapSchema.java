package hexlet.code.schemas;

import java.util.Map;

/**
 * Schema for validating Map objects.
 */
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

        Map<?, ?> mapValue = (Map<?, ?>) value;

        if (this.size != null && mapValue.size() != this.size) {
            return false;
        }

        if (this.schemas != null) {
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

    /**
     * Sets the schema as required (non-null).
     * @return this schema instance
     */
    public MapSchema required() {
        this.required = true;
        return this;
    }

    /**
     * Sets the required size for the map.
     * @param mapSize required size of the map
     * @return this schema instance
     */
    public MapSchema sizeof(int mapSize) {
        this.size = mapSize;
        return this;
    }

    /**
     * Defines the shape of the map with schemas for each key.
     * @param shapeSchemas map of schemas for validation
     * @return this schema instance
     */
    public MapSchema shape(Map<String, BaseSchema<?>> shapeSchemas) {
        this.schemas = shapeSchemas;
        return this;
    }
}
