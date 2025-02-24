package pages;

import config.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //private By payDescription = By.cssSelector("pay-description__cost");
    private By payDescription = By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[1]/span[1]");
    //private By phoneDescription = By.cssSelector("pay-description__text");
    private By phoneDescription = By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[2]/span");
    //private By buttonPay = By.cssSelector(("colored.disabled"));
    private By buttonPay = By.xpath(("/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]/button"));
    //private By numberCard = By.cssSelector("ng-tns-c46-1.ng-star-inserted");
    private By numberCard = By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]/app-card-input/form/div[1]/div[1]/app-input/div/div/div[1]/label");
    //private By validityPeriod = By.cssSelector("ng-tns-c46-4.ng-star-inserted");
    private By validityPeriod = By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]/app-card-input/form/div[1]/div[2]/div[1]/app-input/div/div/div[1]/label");
    //private By cvc = By.cssSelector("ng-tns-c46-5.ng-star-inserted");
    private By cvc = By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]/app-card-input/form/div[1]/div[2]/div[3]/app-input/div/div/div[1]/label");
    //private By cardPerson = By.cssSelector("ng-tns-c46-3.ng-star-inserted");
    private By cardPerson = By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]/app-card-input/form/div[1]/div[3]/app-input/div/div/div[1]/label");
    private By paymentSystemLogosInIframe = By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]/app-card-input/form/div[1]/div[1]/app-input/div/div/div[2]/div/div/img[1]");

    private By selectDropdown = By.className("select__header");
    private By optionItems = By.className("select__option");

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

            if (!isDisplayed) {
                driver.switchTo().defaultContent();
                return false;
            }

            // Проверяем наличие и содержимое полей
            validateFieldText(payDescription, "3.00 BYN");
            validateFieldText(phoneDescription, "Оплата: Услуги связи Номер:375297777777");
            validateFieldText(buttonPay, "Оплатить  3.00 BYN");
            validateFieldText(numberCard, "Номер карты");
            validateFieldText(validityPeriod, "Срок действия");
            validateFieldText(cvc, "CVC");
            validateFieldText(cardPerson, "Имя держателя (как на карте)");

            // Проверяем логотипы платежных систем
            try {
                List<WebElement> logos = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(paymentSystemLogosInIframe));
                if (logos.isEmpty() || logos.stream().anyMatch(logo -> !logo.isDisplayed())) {
                    System.out.println("Не все логотипы отображаются.");
                    driver.switchTo().defaultContent();
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Ошибка при поиске или проверке логотипов: " + e.getMessage());
                driver.switchTo().defaultContent();
                return false;
            }

            // Кликаем на кнопку закрытия, если всплывающее окно найдено
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header__close-button")));
            closeButton.click();

            // Возвращаемся в основной контент страницы
            driver.switchTo().defaultContent();
            return true;

        } catch (Exception e) {
            System.out.println("Ошибка при поиске всплывающего окна или проверке полей: " + e.getMessage());
            driver.switchTo().defaultContent();
            return false;
        }
    }

    /**
     * Вспомогательный метод для проверки текста в элементах
     */
    private void validateFieldText(By locator, String expectedText) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String actualText = element.getText().trim();

            if (!actualText.equals(expectedText)) {
                System.out.println("Ошибка! Несоответствие текста в поле: " + locator);
                System.out.println("Ожидалось: " + expectedText);
                System.out.println("Получено: " + actualText);
            } else {
                System.out.println("Проверено поле " + locator + ": " + actualText);
            }
        } catch (Exception e) {
            System.out.println("Ошибка при проверке поля " + locator + ": " + e.getMessage());
        }
    }

    public boolean verifyPlaceholdersForEachOption() {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(selectDropdown));

            // Получаем текущую выбранную опцию (дефолтную)
            String currentSelection = driver.findElement(By.className("select__now")).getText().trim();

            // Ожидаемые значения плейсхолдеров для каждой опции
            Map<String, String[]> expectedPlaceholders = new HashMap<>();
            expectedPlaceholders.put("Услуги связи", new String[]{"Номер телефона", "Сумма", "E-mail для отправки чека"});
            expectedPlaceholders.put("Домашний интернет", new String[]{"Номер абонента", "Сумма", "E-mail для отправки чека"});
            expectedPlaceholders.put("Рассрочка", new String[]{"Номер счета на 44", "Сумма", "E-mail для отправки чека"});
            expectedPlaceholders.put("Задолженность", new String[]{"Номер счета на 2073", "Сумма", "E-mail для отправки чека"});

            // Проверяем плейсхолдеры для изначально выбранного элемента (до клика в dropdown)
            if (!verifyPlaceholdersForActiveForm(expectedPlaceholders.get(currentSelection))) {
                return false;
            }

            // Открываем dropdown
            dropdown.click();
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(optionItems));

            for (WebElement option : options) {
                String optionText = option.getText().trim();

                // Пропускаем проверку, если это уже выбранный по умолчанию пункт
                if (optionText.equals(currentSelection)) {
                    continue;
                }

                option.click();

                // Пауза 1 секунда для обновления значений
                Thread.sleep(1000);

                // Получаем ожидаемые плейсхолдеры для текущей опции
                String[] expected = expectedPlaceholders.get(optionText);

                if (expected == null) {
                    System.out.println("Ошибка! Не найдены ожидаемые плейсхолдеры для опции: " + optionText);
                    return false;
                }

                // Проверяем плейсхолдеры для активной формы
                if (!verifyPlaceholdersForActiveForm(expected)) {
                    return false;
                }

                // Открываем dropdown снова для следующего выбора
                dropdown.click();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка при проверке плейсхолдеров: " + e.getMessage());
            return false;
        }
    }

    /**
     * Вспомогательный метод для проверки плейсхолдеров в активной форме.
     */
    private boolean verifyPlaceholdersForActiveForm(String[] expected) {
        try {
            // Явно ожидаем, что активная форма с классом "opened" будет доступна
            WebElement activeForm = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form.pay-form.opened")));

            // Определяем классы полей в зависимости от формы
            String phoneInputClass = "phone";
            String sumInputClass = "total_rub";
            String emailInputClass = "email";

            if (activeForm.getAttribute("id").equals("pay-instalment")) { // Форма для рассрочки
                phoneInputClass = "score"; // У формы рассрочки класс телефона "score"
            } else if (activeForm.getAttribute("id").equals("pay-arrears")) { // Форма для задолженности
                phoneInputClass = "score"; // У формы задолженности тоже класс телефона "score"
            }

            // Убедимся, что форма загружена и содержит поля
            wait.until(ExpectedConditions.visibilityOfAllElements(activeForm.findElements(By.tagName("input"))));

            // Проверяем плейсхолдеры для полей в этой форме
            String phonePlaceholder = activeForm.findElement(By.cssSelector("input." + phoneInputClass)).getAttribute("placeholder").trim();
            String sumPlaceholder = activeForm.findElement(By.cssSelector("input." + sumInputClass)).getAttribute("placeholder").trim();
            String emailPlaceholder = activeForm.findElement(By.cssSelector("input." + emailInputClass)).getAttribute("placeholder").trim();

            // Проверка соответствия плейсхолдеров
            if (!expected[0].equals(phonePlaceholder) || !expected[1].equals(sumPlaceholder) || !expected[2].equals(emailPlaceholder)) {
                System.out.println("Ошибка! Несоответствие плейсхолдеров.");
                System.out.println("Ожидалось: " + String.join(", ", expected));
                System.out.println("Получено: " + phonePlaceholder + ", " + sumPlaceholder + ", " + emailPlaceholder);
                return false;
            }

            return true;
        } catch (Exception e) {
            System.out.println("Ошибка при проверке плейсхолдеров: " + e.getMessage());
            return false;
        }
    }

}