package checkMail;

import base.Base;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsPage extends Base {
    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//div[@data-test-id='navigation-menu-item:general' and not(contains(@data-test-active, 'false'))]")
    private WebElement messages;

    @FindBy(xpath = ".//button[@data-test-id=\"edit\"]")
    private WebElement editSignButton;

    @FindBy(xpath = ".//div[@role=\"textbox\"]")
    private WebElement signText;

    @FindBy(xpath = ".//div[@data-test-id='editor']")
    private WebElement signTextForm;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = ".//button[@data-test-id='to-inbox']")
    private WebElement toInboxButton;

    /**
     * Нажимаем на кнопку "Общие".
     */
    @Step("Нажимаем на кнопку \"Общие\".")
    public void goToMessagesSettings() {
        click(messages);
    }

    /**
     * Нажимаем на кнопку редактирования подписи.
     */
    @Step("Нажимаем на кнопку редактирования подписи.")
    public void clickEditSignButton() {
        click(editSignButton);
    }

    /**
     * Редактируем подпись.
     * @param str
     */
    @Step("Редактируем подпись.")
    public void editSignText(final String str) {
        removeText(signText, str);
    }

    /**
     * Нажимаем на кнопку "Сохранить".
     */
    @Step("Нажимаем на кнопку \"Сохранить\".")
    public void clickSaveButton() {
        click(saveButton);
    }

    /**
     * Возвращаемся во входящие.
     */
    @Step("Возвращаемся во входящие.")
    public void returnToInbox() {
        click(toInboxButton);
    }
}