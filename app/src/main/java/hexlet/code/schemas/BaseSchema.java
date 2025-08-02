package hexlet.code.schemas;

import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseSchema {
    private boolean isRequired = false;
    private final List<Predicate<Object>> checks = new ArrayList<>();

    protected final void addCheck(Predicate<Object> check) {
        checks.add(check);
    }

    /**
     * Проверяет валидность значения.
     * @param value Проверяемое значение
     * @return true если значение соответствует схеме
     */
    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired;
        }

        return checks.stream().allMatch(check -> check.test(value));
    }

    /**
     * Помечает поле как обязательное.
     * @return Текущий объект для цепочки вызовов
     */
    public BaseSchema required() {
        this.isRequired = true;
        return this;
    }
}
