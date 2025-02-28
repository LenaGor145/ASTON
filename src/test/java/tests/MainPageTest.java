package tests;

import actions.MainPageActions;
import config.WebDriverSingleton;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Тесты для главной страницы MTS")
@Feature("Проверка функциональности главной страницы")
@ExtendWith(AllureJunit5.class) // Подключение Allure Listener
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainPageTest {
    private MainPageActions mainPageActions;

    @BeforeAll
    public void setup() {
        WebDriverSingleton.getDriver().get("https://www.mts.by/");
        mainPageActions = new MainPageActions();
        mainPageActions.acceptCookies();
    }

    @BeforeEach
    public void init() {
        mainPageActions = new MainPageActions(); // Инициализация объекта перед каждым тестом
    }

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    @Description("Тест проверяет, что заголовок блока 'Онлайн пополнение без комиссии' отображается корректно")
    @Story("Проверка заголовка блока")
    @Severity(SeverityLevel.CRITICAL)
    public void testBlockTitle() {
        assertTrue(mainPageActions.verifyBlockTitle(), "Название блока неверное");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    @Description("Тест проверяет, что логотипы платежных систем отображаются на странице")
    @Story("Проверка логотипов платежных систем")
    @Severity(SeverityLevel.NORMAL)
    public void testPaymentLogos() {
        assertTrue(mainPageActions.checkPaymentLogos(), "Логотипы платежных систем не найдены");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    @Description("Тест проверяет, что ссылка 'Подробнее о сервисе' ведет на правильный URL")
    @Story("Проверка ссылки 'Подробнее о сервисе'")
    @Severity(SeverityLevel.NORMAL)
    public void testMoreInfoLink() {
        mainPageActions.openMoreInfo();
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/",
                WebDriverSingleton.getDriver().getCurrentUrl(),
                "Неверный URL");
    }

    @Test
    @DisplayName("Заполнение формы и проверка всплывающего окна")
    @Description("Тест проверяет, что после заполнения формы появляется всплывающее окно")
    @Story("Проверка формы и всплывающего окна")
    @Severity(SeverityLevel.CRITICAL)
    public void testFillAndSubmit() {
        mainPageActions.enterFills("297777777", "3", "test@test.com");
        mainPageActions.submit();
        assertTrue(mainPageActions.checkResultPopup(), "Всплывающее окно не появилось");
    }

    @Test
    @DisplayName("Проверка placeholders")
    @Description("Тест проверяет корректность плейсхолдеров")
    @Story("Проверка плейсхолдеров")
    @Severity(SeverityLevel.NORMAL)
    public void testPlaceholders() {
        assertTrue(mainPageActions.checkPlaceholders(), "Некорректные плейсхолдеры!");
    }

    @AfterEach
    public void tearDown() {
        WebDriverSingleton.getDriver().manage().deleteAllCookies();
    }

    @AfterAll
    public void quitDriver() {
        WebDriverSingleton.quitDriver();
    }
}