package com.yavlanskiy.model;

public class User {

    private String login;
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
