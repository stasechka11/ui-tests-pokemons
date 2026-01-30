package com.example.tests;

import com.example.core.Props;
import com.example.pages.LoginPage;
import com.example.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {
    @Test
    void login_shouldWork() {

        LoginPage loginPage = new LoginPage(driver);

        MainPage mainPage =
                loginPage
                        .open(Props.get("baseUrl"))
                        .login(Props.get("user"), Props.get("pass"));

        Assertions.assertTrue(
                mainPage.isOpened(),
                "Пользователь должен попасть в личный кабинет"
        );
    }

    @Test
    void login_shouldFail_withWrongPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage
                .open("https://site.com/login")
                .enterUsername("admin")
                .enterPassword("wrong")
                .clickLogin();

        Assertions.assertTrue(
                loginPage.isErrorVisible(),
                "Должна появиться ошибка"
        );
    }
}
