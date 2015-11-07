package com.yavlanskiy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy (css = "#content ul li:nth-child(1) a")
    private WebElement addMovieButton;

    @FindBy (css = ".languages+nav li:nth-child(1) a")
    private WebElement menuHome;

    @FindBy (css = "#q")
    private WebElement findMoviFild;

    @FindBy(css = ".movie_box .title")
    private List<WebElement> listFilms;

    public HomePage filMoviesFild(String request){
        findMoviFild.sendKeys(request,Keys.ENTER);
        return this;
    }

    public AddFilmPage clickAddMovieButton(){
        addMovieButton.click();
        return new AddFilmPage(webDriver);
    }

    public HomePage clickMenuHomeButton(){
        menuHome.click();
        return new HomePage(webDriver);
    }

    public HomePage goTooHomePage(){
        clickMenuHomeButton();
        return new HomePage(webDriver);
    }

    public MovieSettingsPage findFilmForTitle(String nameFilm) {
        filMoviesFild(nameFilm);

        for (WebElement element : listFilms) {
            if (element.getText().equals(nameFilm)) {
                element.click();
            }else {
                break;
            }
        }
        return new MovieSettingsPage(webDriver);
    }

    public HomePage removingFilmByTitle(String title){
        List<WebElement> webElements = webDriver.findElements(By.cssSelector(".movie_box .title"));

        for(WebElement element:listFilms){
            if(element.getText().equals(title)){
                element.click();
            }

            MovieSettingsPage settingsPage = new MovieSettingsPage(webDriver);
            settingsPage.clickRemoveFilmButton();
        }
        return this;
    }
}
