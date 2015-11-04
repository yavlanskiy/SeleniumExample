package com.yavlanskiy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddFilmPage {

    WebDriver webDriver;

    public AddFilmPage(WebDriver webDriver) {
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy (css = "#imdbsearch")
    private WebElement IMDbField;

    @FindBy (xpath = ".//*[@name='name']")
    private WebElement titleFild;

    @FindBy (xpath = ".//*[@name='year']")
    private WebElement yearFild;

    @FindBy (xpath = ".//*[@id='submit']")
    private WebElement submitButton;

    public AddFilmPage setIMDb(String imDb){
        IMDbField.sendKeys(imDb);
        return this;
    }

    public AddFilmPage setTitleFilm(String title){
        titleFild.sendKeys(title);
        return this;
    }

    public AddFilmPage setYearFilm(String year){
        yearFild.sendKeys(year);
        return this;
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public MovieSettingsPage createNewFilm(String title, String year){
        setTitleFilm(title);
        setYearFilm(year);
        clickSubmitButton();
        return new MovieSettingsPage(webDriver);
    }

}
