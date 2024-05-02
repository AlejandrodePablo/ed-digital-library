package com.iesam.digitallibrary.features.presentation;

import com.iesam.digitallibrary.features.user.data.UserDataRepository;
import com.iesam.digitallibrary.features.user.data.local.UserFileLocalDataSource;
import com.iesam.digitallibrary.features.user.domain.*;

import java.util.List;
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

        User newUser = new User(dni, id, name, surname, email, age, telephone);
        NewUserUseCase newUserUseCase = new NewUserUseCase(new UserDataRepository(new UserFileLocalDataSource()));
        newUserUseCase.execute(newUser);
    }

    public static void getUsers() {
        ListUsersUseCase listUsersUseCase = new ListUsersUseCase(new UserDataRepository(new UserFileLocalDataSource()));
        List<User> users = listUsersUseCase.execute();
        System.out.println(users.toString());
    }

    public static void getUser(String id) {
        GetUserUseCase getUserUseCase = new GetUserUseCase(new UserDataRepository(new UserFileLocalDataSource()));
        User user = getUserUseCase.execute(id);
        System.out.println(user.toString());
    }

    public static void deleteUser(String id) {
        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(new UserDataRepository(new UserFileLocalDataSource()));
        deleteUserUseCase.execute(id);
    }

    public static void updateUser() {
        System.out.println("ID del usuario a actualizar: ");
        String id = scanner.nextLine();
        System.out.println("Nuevo DNI: ");
        String dni = scanner.nextLine();
        System.out.println("Nuevo nombre: ");
        String name = scanner.nextLine();
        System.out.println("Nuevo apellido: ");
        String surname = scanner.nextLine();
        System.out.println("Nuevo email: ");
        String email = scanner.nextLine();
        System.out.println("Nueva edad: ");
        String age = scanner.nextLine();
        System.out.println("Nuevo teléfono: ");
        String telephone = scanner.nextLine();

        User updatedUser = new User(dni, id, name, surname, email, age, telephone);
        UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(new UserDataRepository(new UserFileLocalDataSource()));
        updateUserUseCase.execute(updatedUser);
    }
}
