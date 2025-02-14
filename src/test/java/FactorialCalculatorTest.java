import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

public class FactorialCalculatorTest {

    // Позитивные тесты
    @ParameterizedTest
    @DisplayName("Позитивные тесты: факториал чисел >= 1")
    @CsvSource({
            "1, 1",
            "5, 120",
            "10, 3628800",
            "20, 2432902008176640000"
    })
    void testFactorialPositiveCases(int n, long expected) {
        assertEquals(expected, FactorialCalculator.factorial(n));
    }

    // Негативные тесты
    @ParameterizedTest
    @DisplayName("Негативные тесты: факториал отрицательных чисел")
    @ValueSource(ints = {-1, -5, -10})
    void testFactorialNegativeCases(int n) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> FactorialCalculator.factorial(n));
        assertEquals("Число должно быть неотрицательным", exception.getMessage());
    }

    @Test
    @DisplayName("Негативные тесты: факториал 0")
    void testFactorialOfZero() {
        // Факториал 0 должен возвращать 1, но это можно считать граничным случаем
        assertEquals(1, FactorialCalculator.factorial(0));
    }

    // Дополнительный тест для проверки граничного случая (ноль)
    @Test
    @DisplayName("Граничный случай: факториал 0")
    void testFactorialBoundaryCaseZero() {
        assertEquals(1, FactorialCalculator.factorial(0));
    }
}