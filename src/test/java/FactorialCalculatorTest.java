import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

public class FactorialCalculatorTest {

    @ParameterizedTest
    @DisplayName("Факториал неотрицательных чисел")
    @CsvSource({
            "0, 1",
            "1, 1",
            "5, 120",
            "10, 3628800",
            "20, 2432902008176640000"
    })
    void testFactorial(int n, long expected) {
        assertEquals(expected, FactorialCalculator.factorial(n));
    }

    @ParameterizedTest
    @DisplayName("Факториал отрицательных чисел должен выбрасывать исключение")
    @ValueSource(ints = {-1, -5, -10})
    void testFactorialOfNegativeNumber(int n) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> FactorialCalculator.factorial(n));
        assertEquals("Число должно быть неотрицательным", exception.getMessage());
    }
}