package com.iesam;

import com.iesam.digitallibrary.features.domain.User;
import com.iesam.digitallibrary.features.presentation.UserPresentation;

public class Main {
    public static void main(String[] args) {
        createUser();
    }


    public static void createUser() {
        UserPresentation.createUser();
    }

}