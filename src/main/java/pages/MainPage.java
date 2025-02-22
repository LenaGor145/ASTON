package pages;

import config.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage() {
        this.driver = WebDriverSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Локаторы
    private By cookieAcceptButton = By.id("cookie-agree");
    private By cookieBlock = By.cssSelector(".cookie.show");
    private By blockTitle = By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2[contains(normalize-space(string()), 'Онлайн пополнение без комиссии')]");
    private By paymentSystemLogos = By.xpath("//*[@id='pay-section']//div[contains(@class, 'pay__partners')]/ul/li/img");
    private By moreInfoLink = By.xpath("//*[@id='pay-section']//a[contains(text(), 'Подробнее о сервисе')]");
    private By phoneNumberField = By.id("connection-phone");
    private By sumField = By.id("connection-sum");
    private By emailField = By.id("connection-email");
    private By continueButton = By.xpath("//button[text()='Продолжить']");
    private By iframeLocator = By.cssSelector(".bepaid-iframe");
    private By resultPopup = By.xpath("/html/body/app-root/div");

    public void acceptCookiesIfPresent() {
        try {
            // Проверяем, отображается ли блок с куками
            WebElement cookieBanner = wait.until(ExpectedConditions.presenceOfElementLocated(cookieBlock));
            if (cookieBanner.isDisplayed()) {
                WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
                acceptButton.click();
                System.out.println("Куки успешно приняты.");
            }
        } catch (Exception e) {
            System.out.println("Блок с куками не появился или уже принят.");
        }
    }

    public boolean isBlockTitleCorrect() {
        try {
            // Ожидаем появления элемента с заголовком
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle));
            // Проверяем, что элемент отображается
            return titleElement.isDisplayed();
        } catch (Exception e) {
            System.out.println("Ошибка при поиске или проверке текста элемента: " + e.getMessage());
            return false;
        }
    }


    public boolean arePaymentLogosDisplayed() {
        try {
            // Ожидание появления хотя бы одного логотипа
            List<WebElement> logos = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(paymentSystemLogos));
            // Проверяем, что логотипы найдены и отображаются
            return !logos.isEmpty() && logos.stream().allMatch(WebElement::isDisplayed);
        } catch (Exception e) {
            System.out.println("Ошибка при поиске или проверке логотипов: " + e.getMessage());
            return false;
        }
    }

    public void clickMoreInfo() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(moreInfoLink));
        link.click();
        // Ожидание смены URL после клика
        wait.until(ExpectedConditions.urlContains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));
    }

    public void enterPhoneNumber(String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberField)).sendKeys(phone);
    }

    public void enterSum(String sum) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sumField)).sendKeys(sum);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public boolean isResultPopupDisplayed() {
        try {
            // Переключаемся в iframe
            WebElement iframeElement = wait.until(ExpectedConditions.presenceOfElementLocated(iframeLocator));
            driver.switchTo().frame(iframeElement);
            // Ожидаем появление всплывающего окна
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(resultPopup));
            boolean isDisplayed = popup.isDisplayed();

            if (isDisplayed) {
                // Кликаем на кнопку закрытия
                WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header__close-button")));
                closeButton.click();
            }
            // Возвращаемся в основной контент страницы
            driver.switchTo().defaultContent();
            return isDisplayed;
        } catch (Exception e) {
            System.out.println("Ошибка при поиске всплывающего окна: " + e.getMessage());
            return false;
        }
    }
}