package com.iesam.digitallibrary.features.presentation;

import com.iesam.digitallibrary.features.data.UserDataRepository;
import com.iesam.digitallibrary.features.data.local.UserFileLocalDataSource;
import com.iesam.digitallibrary.features.data.local.UserMemLocalDataSource;
import com.iesam.digitallibrary.features.domain.NewUserUseCase;
import com.iesam.digitallibrary.features.domain.User;

import java.util.Scanner;

public class UserPresentation {

    static Scanner scanner = new Scanner(System.in);

    public static void createUser() {

        System.out.println("DNI: ");
        String dni = scanner.nextLine();
        System.out.println("ID: ");
        String id = scanner.nextLine();
        System.out.println("Nombre: ");
        String name = scanner.nextLine();
        System.out.println("Apellido: ");
        String surname = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Edad: ");
        String age = scanner.nextLine();
        System.out.println("Telefono: ");
        String telephone = scanner.nextLine();

        scanner.close();

        User newUser = new User(id, dni, name, surname, email, age, telephone);
        NewUserUseCase newUserUseCase = new NewUserUseCase(new UserDataRepository(new UserMemLocalDataSource()));
        newUserUseCase.execute(newUser);
    }
}
