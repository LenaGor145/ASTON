package tests;

import actions.MainPageActions;
import config.WebDriverSingleton;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainPageTest {
    private MainPageActions mainPageActions;

    @BeforeAll
    public static void setup() {
        WebDriverSingleton.getDriver().get("https://www.mts.by/");
        MainPageActions mainPageActions = new MainPageActions();
        mainPageActions.acceptCookies();  // Принимаем куки сразу после загрузки страницы
    }

    @BeforeEach
    public void init() {  // Создаем объект действий перед каждым тестом
        mainPageActions = new MainPageActions();
    }

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void testBlockTitle() {
        assertTrue(mainPageActions.verifyBlockTitle(), "Название блока неверное");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void testPaymentLogos() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue(mainPageActions.checkPaymentLogos(), "Логотипы платежных систем не найдены");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testMoreInfoLink() {
        mainPageActions.openMoreInfo();
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", WebDriverSingleton.getDriver().getCurrentUrl(), "Неверный URL");
    }

    @Test
    @DisplayName("Заполнение формы и проверка всплывающего окна")
    public void testFillAndSubmit() {
        mainPageActions.enterFills("297777777", "3", "test@test.com");
        mainPageActions.submit();
        assertTrue(mainPageActions.checkResultPopup(), "Всплывающее окно не появилось");
    }

    @Test
    @DisplayName("Проверка placeholders")
    public void testPlaceholders() {
        assertTrue(mainPageActions.checkPlaceholders(), "Некорректные плейсхолдеры!");
    }

    @AfterAll
    public static void tearDown() {
        WebDriverSingleton.quitDriver();
    }
}