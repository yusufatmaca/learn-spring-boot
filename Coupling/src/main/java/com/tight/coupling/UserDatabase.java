package com.tight.coupling;

public class UserDatabase {
    public String getUserDetails() {
        // Directly access DB here
        return "User Details from DB";
    }
}
