package checkMail;

import base.Base;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends Base {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//input[@name='login']")
    private WebElement login;

    @FindBy(xpath = ".//button[@id='mailbox:submit-button']")
    private WebElement enterButton;

    @FindBy(xpath = ".//input[@name='password']")
    private WebElement password;

    /**
     * Ввводим логин в поле логина
     * @param str
     */
    @Step("Ввводим логин в поле логина")
    public void setLogin(final String str) {
        setText(login, str);
    }

    /**
     * Вводим пароль в поле пароля
     * @param str
     */
    @Step("Вводим пароль в поле пароля")
    public void setPassword(final String str) {
        setText(password, str);
    }

    /**
     * Нажимаем кнопку "Войти"
     */
    @Step
    public void enterButton() {
        click(enterButton);
    }
}