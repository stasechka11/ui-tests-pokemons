package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By usernameInput = By.id("k_email");
    private final By passwordInput = By.id("k_password");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.cssSelector("");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Открыть страницу
    public LoginPage open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        return this;
    }

    //Ввести логин
    public LoginPage enterUsername(String username) {
        WebElement loginInputEl = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        loginInputEl.clear();
        loginInputEl.sendKeys(username);
        return this;
    }

    //Ввести пароль
    public LoginPage enterPassword(String password) {
        WebElement passInputEl = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passInputEl.clear();
        passInputEl.sendKeys(password);
        return this;
    }

    //Нажать логин
    public MainPage clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return new MainPage(driver);
    }

    //Метод логина
    public MainPage login(String username, String password) {
        return this
                .enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    //Проверка сообщения об ошибке
    public boolean isErrorVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
    }
}
