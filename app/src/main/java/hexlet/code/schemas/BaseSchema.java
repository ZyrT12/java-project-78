package hexlet.code.schemas;

import java.util.function.Predicate;
import java.util.Objects;

/**
 * Базовый класс схемы валидации.
 * Предоставляет общую функциональность для всех схем валидации.
 */
public abstract class BaseSchema<T> {
    private boolean required;  // флаг обязательности значения
    private Predicate<T> validation = value -> true;  // предикат для валидации

    /**
     * Проверяет валидность значения согласно схеме.
     * @param value Проверяемое значение (может быть null)
     * @return true если значение валидно, false если нет
     */
    @SuppressWarnings("unchecked")
    public boolean isValid(Object value) {
        if (value == null) {
            return !required;  // null допустим только если поле не обязательное
        }

        try {
            return validation.test((T) value);  // проверка значения
        } catch (ClassCastException e) {
            return false;  // неверный тип
        }
    }

    /**
     * Устанавливает поле как обязательное (не может быть null).
     * @return текущий объект схемы для цепочки вызовов
     */
    public  BaseSchema<T> required() {
        required = true;
        addCheck(Objects::nonNull);  // добавляем проверку на null
        return this;
    }

    /**
     * Добавляет новое правило валидации.
     * @param check Предикат для проверки значения
     */
    protected final void addCheck(Predicate<T> check) {
        validation = validation.and(check);  // комбинируем предикаты
    }

    /**
     * Проверяет, является ли поле обязательным.
     * @return true если поле обязательное
     */
    protected final boolean isRequired() {
        return required;
    }

    /**
     * Возвращает текущий предикат валидации.
     * @return предикат валидации
     */
    protected final Predicate<T> getValidation() {
        return validation;
    }
}