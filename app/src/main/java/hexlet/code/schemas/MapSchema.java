package hexlet.code.schemas;

import java.util.Map;

/**
 * Validation schema for map values.
 */
public final class MapSchema extends BaseSchema<Map<?, ?>> {

    /**
     * Marks the map as required (non-null and instance of Map).
     *
     * @return the current schema instance
     */
    public MapSchema required() {
        super.required();
        addCheck(value -> value instanceof Map);
        return this;
    }

    /**
     * Adds a rule that the map must contain exactly the given number of entries.
     *
     * @param size the expected size of the map
     * @return the current schema instance
     */
    public MapSchema sizeof(int size) {
        addCheck(map -> map.size() == size);
        return this;
    }

    /**
     * Adds a rule to validate the shape of the map, based on expected keys and their respective schemas.
     *
     * @param schemas a map of expected keys and their corresponding validation schemas
     * @param <T> the type of the values in the map
     * @return the current schema instance
     */
    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCheck(map -> schemas.entrySet().stream()
                .allMatch(entry -> {
                    String key = entry.getKey();
                    BaseSchema<?> schema = entry.getValue();
                    Object value = map.get(key);
                    return schema.isValid(value);
                })
        );
        return this;
    }
}
