package com.yavlanskiy;

import com.yavlanskiy.model.Film;
import com.yavlanskiy.model.User;
import com.yavlanskiy.pages.AddFilmPage;
import com.yavlanskiy.pages.HomePage;
import com.yavlanskiy.pages.LoginPage;
import com.yavlanskiy.pages.MovieSettingsPage;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Task3 extends TestBase {

    HomePage homePage;
    LoginPage loginPage;
    AddFilmPage addFilmPage;
    MovieSettingsPage settingsPage;

    @BeforeClass
    public void setUp(){
        User admin = new User()
                .setLogin("admin")
                .setPassword("admin");

        driver.get(baseUrl + "/php4dvd/");
        loginPage = new LoginPage(driver);
        homePage = loginPage.logIn(admin.getLogin(),admin.getPassword());
    }

    @Test
    public void correctDataFilm (){
        Film film =new Film()
                .setTitle("TED")
                .setYear("2015");

        addFilmPage = homePage.clickAddMovieButton();
        settingsPage = addFilmPage.createNewFilm(film.getTitle(),film.getYear());

        assertEquals("TED (2015)",
                driver.findElement(By.cssSelector(".maininfo_full h2"))
                        .getText());

        settingsPage.clickRemoveFilmButton();
    }

    @Test
    public void incorrectDataFilm(){
        Film film =new Film()
                .setTitle("TED")
                .setYear("");

        addFilmPage = homePage.clickAddMovieButton();
        settingsPage = addFilmPage.createNewFilm(film.getTitle(), film.getYear());

        assertTrue(driver.findElement(By.cssSelector("tr:nth-child(4) .error:nth-child(2)")).isDisplayed());
        assertEquals("This field is required",
                driver.findElement(By.cssSelector("tr:nth-child(4) .error:nth-child(2)")).getText());
    }

    @AfterClass
    public void close(){
        driver.close();
        driver.quit();
    }
}
