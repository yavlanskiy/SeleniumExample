package com.yavlanskiy;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends TestBase {
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    WebElement usernameField;
    WebElement passwordField;
    WebElement submitButton;
    WebElement myRrofileLinck;

    @Test
    public void recorderTest() throws Exception {
        driver.get(baseUrl + "/php4dvd/");

        usernameField = driver.findElement(By.id("username"));
        usernameField.clear();
        usernameField.sendKeys("admin");

        passwordField = driver.findElement(By.name("password"));
        passwordField.clear();
        passwordField.sendKeys("admin");

        submitButton = driver.findElement(By.name("submit"));
        submitButton.click();

        myRrofileLinck = driver.findElement(By.cssSelector("nav ul>li:nth-child(2) a"));
        myRrofileLinck.click();

        assertEquals("Edit admin",
                driver.findElement(By.cssSelector(".content>h2"))
                        .getText());
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
