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

    @FindBy(xpath = ".//div[@data-testid='mailbox-head']//following::button[@type = 'button']")
    private WebElement enterLoginButton;

    @FindBy(xpath = ".//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = ".//div[@id = 'mailbox']//following::button[@type = 'button']")
    private WebElement enterPassButton;

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
     * Нажимаем кнопку "Войти" после логина
     */
    @Step("Нажимаем кнопку \"Войти\" после логина")
    public void enterLoginButton() {
        click(enterLoginButton);
    }

    /**
     * Нажимаем кнопку "Войти" после пароля
     */
    @Step("Нажимаем кнопку \"Войти\" после логина")
    public void enterPassButton() {
        click(enterPassButton);
    }
}