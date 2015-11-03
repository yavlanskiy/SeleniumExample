package com.yavlanskiy;

import com.yavlanskiy.pages.HomePage;
import com.yavlanskiy.pages.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Task3 extends TestBase {

    HomePage homePage;
    LoginPage loginPage;

    @BeforeClass
    public void setUp(){
        driver.get(baseUrl + "/php4dvd/");
        loginPage = new LoginPage(driver);
        homePage = loginPage.logIn("admin", "admin");
    }

    @Test
    public void test3(){
        homePage.clickAddMovieButton();
    }

    @AfterClass
    public void close(){
        driver.close();
        driver.quit();
    }
}
