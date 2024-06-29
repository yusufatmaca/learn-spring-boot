package com.loose.coupling;

public class UserDatabaseProvider implements UserDataProvider {
    private String name;
    private String surname;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "UserDatabaseProvider{" +
            "name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            '}';
    }
}
