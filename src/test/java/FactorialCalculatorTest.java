import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialCalculatorTest {

    // Позитивные тесты
    @Test(dataProvider = "positiveData")
    public void testFactorialPositiveCases(int n, long expected) {
        assertEquals(FactorialCalculator.factorial(n), expected);
    }

    @DataProvider(name = "positiveData")
    public Object[][] positiveData() {
        return new Object[][]{
                {1, 1},
                {5, 120},
                {10, 3628800},
                {20, 2432902008176640000L}
        };
    }

    // Негативные тесты
    @Test(dataProvider = "negativeData", expectedExceptions = IllegalArgumentException.class)
    public void testFactorialNegativeCases(int n) {
        FactorialCalculator.factorial(n);
    }

    @DataProvider(name = "negativeData")
    public Object[][] negativeData() {
        return new Object[][]{
                {-1},
                {-5},
                {-10}
        };
    }

    // Граничный случай: факториал 0
    @Test
    public void testFactorialOfZero() {
        assertEquals(FactorialCalculator.factorial(0), 1);
    }
}