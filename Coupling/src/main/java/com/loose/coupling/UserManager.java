package com.loose.coupling;

public class UserManager {
    private UserDataProvider userDataProvider;

    public UserManager(UserDataProvider userDataProvider) {
        this.userDataProvider = userDataProvider;
    }

    public void getUserInfo() {
        System.out.println("User Details: " + userDataProvider);
    }
}
