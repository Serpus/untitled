package checkMail;

import base.Base;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class MailBoxPage extends Base {
    public MailBoxPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//span[@class='compose-button__txt']")
    private WebElement writeLetter;

    @FindBy(xpath = ".//div[@class='compose-app__compose']")
    private WebElement letterForm;

    @FindBy(xpath = ".//div[@data-type='to']//input[@type='text']")
    private WebElement to;

    @FindBy(xpath = ".//input[@name='Subject']")
    private WebElement subject;

    @FindBy(xpath = ".//div[@role='textbox']/div")
    private WebElement body;

    @FindBy(xpath = ".//div[contains(@class, 'attach')]//input[@type='file']")
    private WebElement attach;

    @FindBy(xpath = ".//span[@title='Отправить']")
    private WebElement send;

    @FindBy(xpath = ".//div[@class='layer__header']")
    private WebElement captcha;

    @FindBy(xpath = ".//a[contains(@title, 'Входящие')]")
    private WebElement inbox;

    @FindBy(xpath = ".//span[@title='Закрыть']")
    private WebElement cross;

    @FindBy(xpath = ".//h2")
    private WebElement subjectInOpenLetter;

    @FindBy(xpath = ".//div[contains(@id,'BODY')]/div/div[text()]")
    private WebElement bodyInOpenLetter;

    @FindBy(xpath = ".//span[@class='b-filename__name']")
    private WebElement attachName;

    @FindBy(xpath = ".//span[@class='btn__text btn__text_pad' and text()='Скачать']")
    private WebElement downloadAttachButton;

    @FindBy(xpath = ".//span[@class='button2__txt' and text()='Настройки']")
    private WebElement settingsKMButton;

    @FindBy(xpath = ".//div[@class='list-item__selected']")
    private WebElement AllSettingsButton;

    @FindBy(xpath = ".//div[@data-widget='signature']/div[@class='cke_widget_editable']")
    private WebElement signInLetter;

    @FindBy(xpath = ".//div[@data-signature-widget='content']")
    private WebElement signInLetterInOpenLetter;

    @FindBy(xpath = ".//div[@class='llc__avatar']/following::span[@class='llc__subject']/preceding::button[contains(@class, 'll-av')]")
    private List<WebElement> checkboxes;

    @FindBy(xpath = ".//span[@data-title-shortcut='Del']//span[@class='button2__txt']")
    private WebElement deleteLetterButton;

    @FindBy(xpath = ".//span[@class='ll-sj__normal']")
    private List<WebElement> allSubjectLetters;

    @FindBy(xpath = "(.//div[@class=\"checkbox__box checkbox__box_checked\"]/ancestor::label[@class=\"checkbox__label\"])[3]")
    private WebElement checkboxSorting;

    @FindBy(xpath = ".//form//h1")
    private WebElement blockedEmail;

    /**
     * Проверяем, не заблокирован ли email.
     */
    @Step("Проверяем, не заблокирован ли email.")
    public void checkEmail() {
        sleep(3);
        try {
            if (blockedEmail.getText().equals("Восстановление доступа")) {
                System.out.println("Почта заблокирована");
                getDriver().quit();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Почта не заблокирована");
        }

    }

    /**
     * Нажимаем на кнопку "Настройки".
     */
    @Step("Нажимаем на кнопку \"Настройки\".")
    public void openKMSettingsSort() {
        click(settingsKMButton);
    }

    /**
     * Убираем чекбокс "Группировка писем себе".
     */
    @Step("Убираем чекбокс \"Группировка писем себе\".")
    public void setCheckboxSorting() {
        try {
            clickHiddenElement(checkboxSorting);
        } catch (NoSuchElementException e) {
            System.out.println("Чекбокс \"Группировка писем себе\" уже снят.");
        }
    }

    /**
     * Нажимаем на кнопку "Написать письмо".
     */
    @Step("Нажимаем на кнопку \"Написать письмо\".")
    public void clickButtonWriteLetter() {
        click(writeLetter);
    }

    /**
     * Проверяем, что открылась форма создания письма.
     */
    @Step("Проверяем, что открылась форма создания письма.")
    public void checkLetterForm() {
        assert waitVisiblityElement(letterForm) : "Форма создания письма не открылась";
    }

    /**
     * Вводим занчение в поле "Кому".
     * @param str
     */
    @Step("Вводим занчение в поле \"Кому\".")
    public void writeTo(final String str) {
        setText(to, str);
    }

    /**
     * Вводим текст в поле "Тема".
     * @param str
     */
    @Step("Вводим текст в поле \"Тема\".")
    public void writeSubject(final String str) {
        setText(subject, str);
    }

    /**
     * Вводим текст сообщения.
     * @param str
     */
    @Step("Вводим текст сообщения.")
    public void writeBody(final String str) {
        setText(body, str);
    }

    /**
     * Прикрепляем файл с ПК.
     * @param str
     */
    @Step("Прикрепляем файл с ПК.")
    public void setAttach(final String str) {
        setTextHiddenElement(attach,str);
    }

    /**
     * Нажимаем на кнопку "Отправить".
     */
    @Step("Нажимаем на кнопку \"Отправить\".")
    public void clickSendButton() {
        click(send);
    }

    /**
     * Проверяем наличие капчи.
     */
    @Step("Проверяем наличие капчи.")
    public void checkCaptcha() {
        waitVisiblityElement(captcha);
        try {
            if (captcha.getText().equals("Введите код с картинки")) {
                System.out.println("Появилась капча.");
                getDriver().quit();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Капча не появилась");
        }
    }

    /**
     * Нажимаем на крестик после отправки письма.
     */
    @Step("Нажимаем на крестик после отправки письма.")
    public void clickOnCross() {
        click(cross);
    }

    @FindBy(xpath = ".//a[contains(@title, 'Отправленные')]")
    private WebElement outbox;

    /**
     * Нажимаем на "Исходящие".
     */
    @Step("Нажимаем на \"Исходящие\".")
    public void clickOutbox() {
        click(outbox);
    }

    /**
     * Нажимаем на кнопку "Вхоодящие".
     */
    @Step("Нажимаем на кнопку \"Входящие\".")
    public void clickInbox() {
        click(inbox);
    }


    /**
     * Проверяем, что письмо с темой указканной в шаге 5 пристуствует в списке.
     */
    final Map<Integer, String> subjectSendedLetterMap = new HashMap<Integer,String>();
    @Step("Проверяем, что письмо с темой указканной в шаге 5 пристуствует в списке..")
    public void checkSubjectOfSendedLetter(final String str) {
        sleep(4);
        waitVisiblityElement(allSubjectLetters);
        for (WebElement x: allSubjectLetters) {
            if (x.getText().equals("Ваше сообщение не доставлено. Mail failure.")) {
                System.out.println("Сработал анти-спам");
                getDriver().quit();
            }
        }
        Integer i=0;
        for (WebElement x: allSubjectLetters) {
            subjectSendedLetterMap.put(i, x.getText());
            i++;
        }
        Assert.assertEquals(true, subjectSendedLetterMap.containsValue(str));
    }

    /**
     * Открываем письмо из шага 5.
     * @param str
     */
    @Step("Открываем письмо из шага 5..")
    public void openLetter(final String str) {
        waitVisiblityElement(allSubjectLetters);
        for (WebElement x: allSubjectLetters)
            if (str.equals(x.getText())) {
                click(x);
                break;
            }
    }

    /**
     * Проверяем, что тема письма соответстует теме из шага 5.
     * @param str
     */
    @Step("Проверяем, что тема пибма соответстует теме из шага 5.")
    public void checkSubjectInOpenLetter(final String str) {
        waitVisiblityElement(subjectInOpenLetter);
        Assert.assertEquals(str, subjectInOpenLetter.getText());
    }

    /**
     * Проверяем, что текст письма соответстует тексту из шага 5.
     * @param str
     */
    @Step("Проверяем, что текст письма соответстует тексту из шага 5.")
    public void checkBodyInOpenLetter(final String str) {
        Assert.assertEquals(str, bodyInOpenLetter.getText());
    }

    /**
     * Проверяем, что прикреплённый документ присутствует в письме.
     * @param str
     */
    @Step("Проверяем, что прикреплённый документ присутствует в письме.")
    public void checkAttach(final String str) {
        Assert.assertEquals(str, attachName.getText());
    }

    /**
     * Скачиваем прикреплённый документ.
     */
    @Step("Скачиваем прикреплённый документ.")
    public void downloadAttach() {
        clickHiddenElement(downloadAttachButton);
        sleep(5);
    }

    /**
     * Проверяем доступность скачанного документа.
     */
    @Step("Проверяем доступность скачанного документа.")
    public void checkDownloadedAttach () {
        getDriver().get("file:///" + System.getProperty("user.dir") + "\\" + attachName.getText());
        try {
            Assert.assertNotEquals("ERR_FILE_NOT_FOUND", getDriver().findElement(By.className("error-code")).getText());
        } catch (NoSuchElementException e) {
            System.out.println("Проврека доступности скачанного документа: Файл скачан и доступен.");
        }
        getDriver().navigate().back();
        getDriver().navigate().back();
    }

    /**
     * Нажимаем на кнопку "Настройки".
     */
    @Step("Нажимаем на кнопку \"Настройки\".")
    public void openKMSettings() {
        click(settingsKMButton);
    }

    /**
     * Нажимаем на кнопку "Все настройки".
     */
    @Step("Нажимаем на кнопку \"Все настройки\".")
    public void goToSettings() {
        click(AllSettingsButton);
        ArrayList<String> tabs = new ArrayList (getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
    }

    /**
     * Проверяем изменённую подпись.
     * @param str
     */
    @Step("Проверяем изменённую подпись.")
    public void checkEditedSign(final String str) {
        waitVisiblityElement(signInLetter);
        Assert.assertEquals(str, signInLetter.getText());
    }

    /**
     * Проверяем изменённую подпись в отправленном письме.
     * @param str
     */
    @Step("Проверяем изменённую подпись.")
    public void checkEditedSignInSendedLetter(final String str) {
        waitVisiblityElement(signInLetterInOpenLetter);
        Assert.assertEquals(str, signInLetterInOpenLetter.getText());
    }

    /**
     * Возвращаемся во входящие.
     */
    @Step("Возвращаемся во входящие.")
    public void returnToInbox() {
        getDriver().navigate().back();
    }

    /**
     * Отмечаем чекбоксы напротив писем.
     */
    @Step("Отмечаем чекбоксы напротив писем.")
    public void setCheckboxes() {
        sleep(5);
        for (WebElement x: checkboxes) click(x);
    }

    /**
     * Удаляем отправленные письма.
     */
    @Step("Удаляем отправленные письма.")
    public void deleteSendedLetters() {
        click(deleteLetterButton);
    }

    /**
     * Проверяем наличие писем после удаления.
     * @param str
     */
    @Step("Проверяем наличие писем после удаления.")
    public void checkSubjectsLetters(final String ... str ) {
        waitVisiblityElement(allSubjectLetters);
        for (WebElement x: allSubjectLetters)
            for (String y: str)
            Assert.assertNotEquals(y, x.getText());
    }
}