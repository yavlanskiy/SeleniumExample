package com.yavlanskiy.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MovieSettingsPage {

    WebDriver webDriver;

    public MovieSettingsPage(WebDriver webDriver) {
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "#content ul li:nth-child(4) a")
    private WebElement removeFilmButton;

    public void clickRemoveFilmButton(){
        removeFilmButton.click();
        webDriver.switchTo().alert().accept();
        webDriver.switchTo().parentFrame();
    }

}
