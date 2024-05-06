package com.iesam;

import com.iesam.digitallibrary.features.ebook.presentation.EBookPresentation;
import com.iesam.digitallibrary.features.user.presentation.UserPresentation;


public class Main {
    public static void main(String[] args) {
        //createUser();
        //getUsers();
        //getUser();
        //updateUser();
        //deleteUser();

        //createEBook();
        //deleteEBook();
        //getEBook();
        getEbooks();
    }


    public static void createUser() {
        UserPresentation.createUser();
    }

    public static void getUsers() {
        UserPresentation.getUsers();
    }

    public static void getUser() {
        UserPresentation.getUser();
    }


    public static void deleteUser() {
        UserPresentation.deleteUser();
    }

    public static void updateUser() {
        UserPresentation.updateUser();
    }

    public static void createEBook() {
        EBookPresentation.createEBook();
    }

    public static void deleteEBook() {
        EBookPresentation.deleteEBook();
    }

    public static void getEBook(){
        EBookPresentation.getEBook();
    }

    public static void getEbooks(){
        EBookPresentation.getEbooks();
    }
}