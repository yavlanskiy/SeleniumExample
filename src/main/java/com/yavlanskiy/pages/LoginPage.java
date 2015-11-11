package com.yavlanskiy.pages;

import com.yavlanskiy.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Page {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "submit")
    private WebElement submitButton;

    public LoginPage setUsernameField(String username){
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage setPasswordField(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

//    public HomePage logIn(String username, String password){
//        setUsernameField(username);
//        setPasswordField(password);
//        clickSubmitButton();
//        return new HomePage(webDriver);
//    }

    public HomePage logIn(User user){
        setUsernameField(user.getLogin());
        setPasswordField(user.getPassword());
        clickSubmitButton();
        return new HomePage(webDriver);
    }

}
