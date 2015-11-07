package com.yavlanskiy;

import com.yavlanskiy.model.Film;
import com.yavlanskiy.model.User;
import com.yavlanskiy.pages.AddFilmPage;
import com.yavlanskiy.pages.HomePage;
import com.yavlanskiy.pages.LoginPage;
import com.yavlanskiy.pages.MovieSettingsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class Task4 extends TestBase {

    private LoginPage loginPage;
    private HomePage homePage;
    private AddFilmPage addFilmPage;
    MovieSettingsPage settingsPage;


    @BeforeClass
    public void setUp(){
        User admin = new User()
                .setLogin("admin")
                .setPassword("admin");

        driver.get(baseUrl + "/php4dvd/");
        loginPage = new LoginPage(driver);
        homePage = loginPage.logIn(admin.getLogin(), admin.getPassword());
    }

    @Test
    public void filmsFound(){
        Film film = new Film()
                .setTitle("Побег из Шоушенка")
                .setYear("1994");

        addFilmPage = homePage.clickAddMovieButton();
        settingsPage = addFilmPage.createNewFilm(film.getTitle(), film.getYear());

        homePage.goTooHomePage();
        homePage.findFilmForTitle("Побег из Шоушенка");

        assertEquals(film.getTitle() + " (" + film.getYear() + ")",
                driver.findElement(By.cssSelector(".maininfo_full>h2"))
                        .getText());

        settingsPage.clickRemoveFilmButton();
    }

    @Test
    public void moviesNotFound(){
        homePage.filMoviesFild("Гладиатор");

        assertEquals("No movies where found.",
                driver.findElement(By.cssSelector(".content"))
                        .getText());
    }

    @AfterClass
    public void close(){
        driver.close();
        driver.quit();
    }
}
