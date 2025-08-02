package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {
    private static final int MIN_LENGTH_3 = 3;

    @Test
    void testBasicValidation() {
        Validator v = new Validator();
        var schema = v.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("test"));
    }

    @Test
    void testRequired() {
        var schema = new Validator().string().required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hello"));
    }

    @Test
    void testMinLength() {
        var schema = new Validator().string().minLength(MIN_LENGTH_3);
        assertTrue(schema.isValid("hex"));
        assertFalse(schema.isValid("he"));
    }

    @Test
    void testContains() {
        var schema = new Validator().string().contains("ex");
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("hello"));
    }
}
