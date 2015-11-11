package com.yavlanskiy;

import com.yavlanskiy.model.User;
import com.yavlanskiy.pages.HomePage;
import com.yavlanskiy.pages.LoginPage;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SerchTest extends TestBase {
    private boolean acceptNextAlert = true;
    private String searchTerm = "Побег";
    private User admin;

    @BeforeClass
    public void addMovie() {

        admin = new User()
                .setLogin("admin")
                .setPassword("admin");

        driver.get(baseUrl + "/php4dvd/#!/sort/name%20asc/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(admin);

        driver.findElement(By.cssSelector("nav a[href*=\"/?go=add\"]"))
                .click();
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys(searchTerm);
        driver.findElement(By.name("year")).clear();
        driver.findElement(By.name("year")).sendKeys("2000");
        driver.findElement(By.id("submit")).click();
    }

    @AfterClass
    public void deleteMovie() throws Exception {
        driver.get(baseUrl + "/php4dvd/#!/sort/name%20asc/");
        driver.findElement(By.cssSelector("#results .movie_box"))
                .click();
        driver.findElement(By.cssSelector("nav a[onclick*=\"/?go=delete&id=\"]"))
                .click();
        driver.switchTo().alert().accept();
    }


    @Test
    public void testSearchNoResults() throws Exception {
        driver.get(baseUrl + "/php4dvd/#!/sort/name%20asc/");

        WebElement searchInput = driver.findElement(By.id("q"));
        searchInput.clear();
        String searchTerm = String.valueOf(System.currentTimeMillis());
        searchInput.sendKeys(searchTerm);
        searchInput.sendKeys(Keys.RETURN);

        Assert.assertTrue(isElementPresent(By.cssSelector("#results .content")));
    }

    @Test
    public void testSearchWithResults() throws Exception {
        driver.get(baseUrl + "/php4dvd/#!/sort/name%20asc/");

        WebElement searchInput = driver.findElement(By.id("q"));
        searchInput.clear();
        searchInput.sendKeys(searchTerm);
        searchInput.sendKeys(Keys.RETURN);
        Assert.assertTrue(isElementPresent(By.cssSelector("#results .movie_box")));
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
