package com.yavlanskiy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
}
