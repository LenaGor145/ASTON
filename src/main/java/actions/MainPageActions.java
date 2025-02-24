package actions;

import pages.MainPage;

public class MainPageActions {
    private MainPage mainPage;

    public MainPageActions() {
        this.mainPage = new MainPage();
    }

    public void acceptCookies() {
        mainPage.acceptCookiesIfPresent();
    }

    public boolean verifyBlockTitle() {
        return mainPage.isBlockTitleCorrect();
    }

    public boolean checkPaymentLogos() {
        return mainPage.arePaymentLogosDisplayed();
    }

    public void openMoreInfo() {
        mainPage.clickMoreInfo();
    }

    public void enterFills(String phone, String sum, String email) {
        mainPage.enterPhoneNumber(phone);
        mainPage.enterSum(sum);
        mainPage.enterEmail(email);
    }

    public void submit() {
        mainPage.clickContinue();
    }

    public boolean checkResultPopup() {
        return mainPage.isResultPopupDisplayed();
    }

    public boolean checkPlaceholders() {
        return mainPage.verifyPlaceholdersForEachOption();
    }
}
