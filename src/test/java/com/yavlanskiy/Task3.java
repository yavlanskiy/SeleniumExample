package com.yavlanskiy;

import com.yavlanskiy.model.Film;
import com.yavlanskiy.model.User;
import com.yavlanskiy.pages.AddFilmPage;
import com.yavlanskiy.pages.HomePage;
import com.yavlanskiy.pages.LoginPage;
import com.yavlanskiy.pages.MovieSettingsPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.TestNG;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

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
        homePage = loginPage.logIn(admin);
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
        homePage.goTooHomePage();
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
        homePage.goTooHomePage();
    }

    @Test
    public void deleteFilm() throws Exception {
        Film film =new Film()
                .setTitle("DELETE")
                .setYear("2020");

        addFilmPage = homePage.clickAddMovieButton();
        settingsPage = addFilmPage.createNewFilm(film.getTitle(), film.getYear());
        settingsPage.clickRemoveFilmButton();

        List<WebElement> webElements = driver.findElements(By.cssSelector(".movie_box .title"));
        for (WebElement element:webElements){
            if(element.getText().equals(film.getTitle())){
                throw new Exception("Element is not deleted");
            }
        }
    }

    @AfterClass
    public void close(){
        driver.close();
        driver.quit();
    }
}
