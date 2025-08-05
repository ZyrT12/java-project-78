package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {

    @Test
    void testBasicValidation() {
        Validator v = new Validator();
        var schema = v.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(-3));
        assertTrue(schema.isValid(0));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("not a number"));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(0));

        // Проверка диапазона
        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(7));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testRequired() {
        var schema = new Validator().number().required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    void testPositive() {
        var schema = new Validator().number().positive();
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testRange() {
        var schema = new Validator().number().range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }
}
