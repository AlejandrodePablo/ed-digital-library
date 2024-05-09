package com.iesam;

import com.iesam.digitallibrary.features.ebook.presentation.EBookPresentation;
import com.iesam.digitallibrary.features.user.presentation.UserPresentation;


public class Main {
    public static void main(String[] args) {
        //createUser();
        //getUsers();
        //getUser();
        //deleteUser();
        //updateUser();
        //getUsers();

        createEBook();
        deleteEbook();
    }


    public static void createUser() {
        UserPresentation.createUser();
    }

    public static void getUsers() {
        UserPresentation.getUsers();
    }

    public static void getUser() {
        UserPresentation.getUser("01");
    }

    public static void deleteUser() {
        UserPresentation.deleteUser("02");
    }

    public static void updateUser() {
        UserPresentation.updateUser();
    }

    public static void createEBook() {
        EBookPresentation.createEBook();
    }

    public static void deleteEbook() {
        EBookPresentation.deleteEBook();
    }
}