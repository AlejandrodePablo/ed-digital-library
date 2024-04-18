package com.iesam;

import com.iesam.digitallibrary.features.domain.User;
import com.iesam.digitallibrary.features.presentation.UserPresentation;

public class Main {
    public static void main(String[] args) {
        createUser();
    }
    public static void createUser(){
        User user = new User(
                "11111111A",
                "01",
                "Alejandro",
                "de Pablo",
                "alejandro@email.com",
                "20",
                "612345678"
        );
        UserPresentation.createUser(user);
    }
}