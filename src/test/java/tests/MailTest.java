package tests;

import baseTest.BaseTest;
import checkMail.MailBoxPage;
import checkMail.MainPage;
import checkMail.SettingsPage;
import org.junit.Test;

public class MailTest extends BaseTest {
    @Test
    public void startTest() {
        final String strSubject = "Тестовое письмо",
                login = "cwd12.dwad21@list.ru",
                password = "PUToAp3-ury1",
                attachName = "Тестовый текстовый файл.txt",
                attachName2 = "5.doc",
                newSign = "--\nEdited Sign",
                bodyText = "Рис, гречка два отличных источника углеводов. " +
                "Именно их при диете можно употреблять в небольших порциях. сли уж без кофе совсем никак, " +
                "тогда лучше пить его уже после еды, и с добавлением молока. Для правильного перекуса прекрасно подойдет яблоко, " +
                "стакан кефира или горсть орехов. Правильный завтрак спортсмена или человека с высокой " +
                "физической активностью, отличается от завтрака обычного человека. Здоровое питание укрепляет иммунитет, " +
                "особенно если совмещать его с занятиями спортом и прогулками на свежем воздухе.",
                strSubject2 = "Тестовое письмо с новой подписью",
                bodyText2 = "Интересно, " +
                "что первый компьютер был размером с микроавтобус и весил почти тонну. " +
                "В то время как сейчас это может быть маленький чип размером с булавку. " +
                "И компьютерные технологии не стоят на месте, она быстро прогрессируют.";

        openBrowser("https://mail.ru/");
        //Шаг 1
        System.out.println("Шаг 1");
        MainPage open = new MainPage(getDriver());
        open.setLogin(login);
        open.enterButton();
        open.setPassword(password);
        //Шаг 2
        System.out.println("Шаг 2");
        open.enterButton();
        MailBoxPage letter = new MailBoxPage(getDriver());
        letter.checkEmail();
       //Шаг 3
        System.out.println("Шаг 3");
        letter.openKMSettingsSort();
        letter.setCheckboxSorting();
        letter.clickButtonWriteLetter();
        //Шаг 4
        System.out.println("Шаг 4");
        letter.checkLetterForm();
        //Шаг 5
        System.out.println("Шаг 5");
        letter.writeTo(login);
        letter.writeSubject(strSubject);
        letter.writeBody(bodyText);
        letter.setAttach("D:\\JenkinsWorkDir\\" + attachName);
        letter.clickSendButton();
        letter.checkCaptcha();
        //Шаг 6
        System.out.println("Шаг 6");
        letter.clickOnCross();
        letter.clickOutbox();
        letter.clickInbox();
        letter.checkSubjectOfSenedLetter(strSubject);
        //Шаг 7
        System.out.println("Шаг 7");
        letter.openLetter(strSubject);
        letter.checkSubjectInOpenLetter(strSubject);
        letter.checkBodyInOpenLetter(bodyText);
        letter.checkAttach(attachName);
        letter.downloadAttach();
        letter.checkDownloadedAttach();
        //Шаг 8
        System.out.println("Шаг 8");
        letter.openKMSettings();
        letter.goToSettings();
        SettingsPage settings = new SettingsPage(getDriver());
        settings.goToMessagesSettings();
        settings.clickEditSignButton();
        settings.editSignText(newSign);
        settings.clickSaveButton();
        settings.returnToInbox();
        //Шаг 9
        System.out.println("Шаг 9");
        letter.clickButtonWriteLetter();
        letter.checkLetterForm();
        letter.checkEditedSign(newSign);
        //Шаг 10
        System.out.println("Шаг 10");
        letter.writeTo(login);
        letter.writeSubject(strSubject2);
        letter.writeBody(bodyText2);
        letter.setAttach("D:\\JenkinsWorkDir\\" + attachName2);
        letter.clickSendButton();
        letter.checkCaptcha();
        //Шаг 11
        System.out.println("Шаг 11");
        letter.clickOnCross();
        letter.clickOutbox();
        letter.clickInbox();
        letter.checkSubjectOfSenedLetter(strSubject2);
        //Шаг 12
        System.out.println("Шаг 12");
        letter.openLetter(strSubject2);
        letter.checkSubjectInOpenLetter(strSubject2);
        letter.checkBodyInOpenLetter(bodyText2);
        letter.checkEditedSignInSendedLetter(newSign);
        //Шаг 13
        System.out.println("Шаг 13");
        letter.returnToInbox();
        //Шаг 14
        System.out.println("Шаг 14");
        letter.setCheckboxes();
        letter.deleteSendedLetters();
        //Шаг 15
        System.out.println("Шаг 15");
        letter.checkSubjectsLetters(strSubject, strSubject2);
    }
}
