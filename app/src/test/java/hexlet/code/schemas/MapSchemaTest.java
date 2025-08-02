package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MapSchemaTest {

    private static final int TEST_NUMBER_10 = -10;
    private static final int TEST_NUMBER_25 = 25;
    private static final int TEST_NUMBER_30 = 30;

    @Test
    void testMapValidation() {
        Validator v = new Validator();
        var schema = v.map();

        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        schema.sizeof(2);
        Map<String, String> data = new HashMap<>();
        assertFalse(schema.isValid(data));
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());

        var shapeSchema = v.map().shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Alice");
        human1.put("age", TEST_NUMBER_30);
        assertTrue(shapeSchema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Bob");
        human2.put("age", TEST_NUMBER_10);
        assertFalse(shapeSchema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("age", TEST_NUMBER_25);
        assertFalse(shapeSchema.isValid(human3));
    }
}
