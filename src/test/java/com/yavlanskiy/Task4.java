package com.yavlanskiy;

import com.yavlanskiy.model.Film;
import com.yavlanskiy.model.User;
import com.yavlanskiy.pages.AddFilmPage;
import com.yavlanskiy.pages.HomePage;
import com.yavlanskiy.pages.LoginPage;
import com.yavlanskiy.pages.MovieSettingsPage;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class Task4 extends TestBase {

    private LoginPage loginPage;
    private HomePage homePage;
    private AddFilmPage addFilmPage;
    MovieSettingsPage settingsPage;

    Film film;
    User admin;

    @BeforeClass
    public void setUp(){
        initElement();
        driver.get(baseUrl + "/php4dvd/");
        loginPage = new LoginPage(driver);
        homePage = loginPage.logIn(admin);
        addFilmPage = homePage.clickAddMovieButton();
        settingsPage = addFilmPage.createNewFilm(film.getTitle(), film.getYear());
    }

    @Test
    public void testSearchWithResults() throws InterruptedException {
        homePage.goTooHomePage();
        homePage.filMoviesFild(String.valueOf(System.currentTimeMillis()));

        (new WebDriverWait(driver,10)).until(ExpectedConditions.textToBePresentInElementLocated
                (By.cssSelector(".content"), "No movies where found."));
        assertEquals("No movies where found.",
                driver.findElement(By.cssSelector(".content"))
                        .getText());

        homePage.findFilmForTitle(film.getTitle());
        assertEquals(film.getTitle() + " (" + film.getYear() + ")",
                driver.findElement(By.cssSelector(".maininfo_full>h2"))
                        .getText());

        homePage.goTooHomePage();
    }

    @Test
    public void testSearchNoResults(){
        homePage.goTooHomePage();
        homePage.filMoviesFild(String.valueOf(System.currentTimeMillis()));

        (new WebDriverWait(driver,10)).until(ExpectedConditions.textToBePresentInElementLocated
                (By.cssSelector(".content"), "No movies where found."));
        assertEquals("No movies where found.",
                driver.findElement(By.cssSelector(".content"))
                        .getText());
    }

    @AfterClass
    public void close(){
        homePage.filMoviesFild("");
        homePage.removingFilmByTitle(film.getTitle());
        driver.close();
        driver.quit();
    }

    private void initElement(){
        admin = new User()
                .setLogin("admin")
                .setPassword("admin");

        film = new Film()
                .setTitle("Побег из Шоушенка")
                .setYear("1994");
    }
}
