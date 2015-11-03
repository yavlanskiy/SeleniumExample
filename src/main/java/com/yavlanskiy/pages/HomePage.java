package com.yavlanskiy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by KAIN on 03.11.2015.
 */
public class HomePage {

    WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver,this);
    }

    @FindBy (css = "#content ul li:nth-child(1) a")
    private WebElement addMovieButton;

    public void clickAddMovieButton(){
        addMovieButton.click();
    }
}
