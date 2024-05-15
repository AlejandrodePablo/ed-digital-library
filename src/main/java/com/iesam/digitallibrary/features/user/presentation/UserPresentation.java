package com.iesam.digitallibrary.features.user.presentation;

import com.iesam.digitallibrary.features.user.data.UserDataRepository;
import com.iesam.digitallibrary.features.user.data.local.UserFileLocalDataSource;
import com.iesam.digitallibrary.features.user.data.local.UserMemLocalDataSource;
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
        NewUserUseCase newUserUseCase = new NewUserUseCase(new UserDataRepository(UserFileLocalDataSource.getInstance()));
        newUserUseCase.execute(newUser);
    }

    public static void getUsers() {
        ListUsersUseCase listUsersUseCase = new ListUsersUseCase(new UserDataRepository(UserFileLocalDataSource.getInstance()));
        List<User> users = listUsersUseCase.execute();
        System.out.println(users.toString());
    }

    public static void getUser() {
        System.out.println("User ID to list: ");
        String id = scanner.nextLine();

        GetUserUseCase getUserUseCase = new GetUserUseCase(new UserDataRepository(UserFileLocalDataSource.getInstance()));
        User user = getUserUseCase.execute(id);

        if (user != null) {
            System.out.println(user.toString());
        } else {
            System.out.println("User with ID " + id + " does not exist.");
        }
    }

    public static void deleteUser() {
        System.out.println("User ID to delete: ");
        String id = scanner.nextLine();
        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(new UserDataRepository(UserFileLocalDataSource.getInstance()));
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
        UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(new UserDataRepository(UserFileLocalDataSource.getInstance()));
        updateUserUseCase.execute(updatedUser);
    }
}
