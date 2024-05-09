package com.iesam;

import com.iesam.digitallibrary.features.presentation.UserPresentation;

public class Main {
    public static void main(String[] args) {
        //createUser();
        //getUsers();
        //getUser();
        deleteUser();
    }


    public static void createUser() {
        UserPresentation.createUser();
    }

    public static void getUsers() {
        UserPresentation.getUsers();
    }

    public static void getUser() {
        UserPresentation.getUser("1");
    }


    public static void deleteUser() {
        UserPresentation.deleteUser("1");
    }
}