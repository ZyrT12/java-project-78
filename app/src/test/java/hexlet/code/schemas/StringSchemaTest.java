package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class StringSchemaTest {

    @Test
    void stringIsValid() {
        var v = new Validator();
        var schema = v.string();

        assertThat(schema.isValid("")).isTrue();

        schema.required();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();

        schema.minLength(7);
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isFalse();

        assertThat(
                schema.contains("what").isValid("what does the fox say")
        ).isTrue();

        assertThat(
                schema.contains("whatthe").isValid("what does the fox say")
        ).isFalse();

        var schema1 = v.string().required().minLength(10).minLength(4);
        assertThat(schema1.isValid("hexlet")).isTrue();
    }
}
