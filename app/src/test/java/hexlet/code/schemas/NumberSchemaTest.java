package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {

    private static final int TEST_NUMBER_0 = 0;
    private static final int TEST_NUMBER_4 = 4;
    private static final int TEST_NUMBER_5 = 5;
    private static final int TEST_NUMBER_7 = 7;
    private static final int TEST_NUMBER_10 = 10;
    private static final int TEST_NUMBER_11 = 11;
    private static final int TEST_NUMBER_NEG_3 = -3;
    private static final int TEST_NUMBER_NEG_5 = -5;
    private static final int RANGE_MIN = TEST_NUMBER_5;
    private static final int RANGE_MAX = TEST_NUMBER_10;

    @Test
    void testBasicValidation() {
        Validator v = new Validator();
        var schema = v.number();

        // По умолчанию: null и любые числа разрешены
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(TEST_NUMBER_5));
        assertTrue(schema.isValid(TEST_NUMBER_NEG_3));
        assertTrue(schema.isValid(TEST_NUMBER_0));

        // После required: null и не-числа запрещены
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("not a number")); // неправильный тип
        assertTrue(schema.isValid(TEST_NUMBER_5));
        assertTrue(schema.isValid(TEST_NUMBER_0));

        // Проверка диапазона
        schema.range(RANGE_MIN, RANGE_MAX);
        assertTrue(schema.isValid(RANGE_MIN));
        assertTrue(schema.isValid(RANGE_MAX));
        assertTrue(schema.isValid(TEST_NUMBER_7));
        assertFalse(schema.isValid(TEST_NUMBER_4));
        assertFalse(schema.isValid(TEST_NUMBER_11));
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
        var schema = new Validator().number().range(RANGE_MIN, RANGE_MAX);
        assertTrue(schema.isValid(RANGE_MIN));
        assertTrue(schema.isValid(RANGE_MAX));
        assertFalse(schema.isValid(TEST_NUMBER_4));
        assertFalse(schema.isValid(TEST_NUMBER_11));
    }
}
