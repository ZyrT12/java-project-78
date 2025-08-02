package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {
    private static final int TEST_NUMBER_5 = 5;
    private static final int TEST_NUMBER_10 = 10;
    private static final int TEST_NUMBER_NEG_5 = -5;
    private static final int TEST_NUMBER_0 = 0;
    private static final int TEST_NUMBER_4 = 4;
    private static final int TEST_NUMBER_11 = 11;

    @Test
    void testBasicValidation() {
        Validator v = new Validator();
        var schema = v.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(TEST_NUMBER_5));
    }

    @Test
    void testRequired() {
        var schema = new Validator().number().required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(TEST_NUMBER_10));
    }

    @Test
    void testPositive() {
        var schema = new Validator().number().positive();
        assertTrue(schema.isValid(TEST_NUMBER_5));
        assertFalse(schema.isValid(TEST_NUMBER_NEG_5));
        assertFalse(schema.isValid(TEST_NUMBER_0));
    }

    @Test
    void testRange() {
        var schema = new Validator().number().range(TEST_NUMBER_5, TEST_NUMBER_10);
        assertTrue(schema.isValid(TEST_NUMBER_5));
        assertTrue(schema.isValid(TEST_NUMBER_10));
        assertFalse(schema.isValid(TEST_NUMBER_4));
        assertFalse(schema.isValid(TEST_NUMBER_11));
    }
}
