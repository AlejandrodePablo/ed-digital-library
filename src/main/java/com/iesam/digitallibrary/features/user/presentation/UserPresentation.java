package com.iesam.digitallibrary.features.user.presentation;

import com.iesam.digitallibrary.features.user.data.UserDataRepository;
import com.iesam.digitallibrary.features.user.data.local.UserFileLocalDataSource;
import com.iesam.digitallibrary.features.user.domain.*;

import java.util.List;
import java.util.Scanner;

public class UserPresentation {

    static Scanner scanner = new Scanner(System.in);

    public void showUserMenu() {
        int opcion;
        do {
            System.out.println("\nMenú de Gestión de Usuarios:");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Eliminar Usuario");
            System.out.println("3. Mostrar Todos los Usuarios");
            System.out.println("4. Mostrar Usuario por ID");
            System.out.println("5. Actualizar un Usuario");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    createUser();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    getUsers();
                    break;
                case 4:
                    getUser();
                    break;
                case 5:
                    updateUser();
                case 6:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (opcion != 6);
    }

    public static void createUser() {


        System.out.println("DNI: ");
        scanner.nextLine();
        String dni = scanner.nextLine();
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

        User newUser = new User(dni, name, surname, email, age, telephone);
        NewUserUseCase newUserUseCase = new NewUserUseCase(new UserDataRepository(UserFileLocalDataSource.getInstance()));
        newUserUseCase.execute(newUser);
    }

    public static void getUsers() {
        ListUsersUseCase listUsersUseCase = new ListUsersUseCase(new UserDataRepository(UserFileLocalDataSource.getInstance()));
        List<User> users = listUsersUseCase.execute();
        System.out.println(users.toString());
    }

    public static User getUser() {
        System.out.println("User ID to list: ");
        scanner.nextLine();
        String id = scanner.nextLine();

        GetUserUseCase getUserUseCase = new GetUserUseCase(new UserDataRepository(UserFileLocalDataSource.getInstance()));
        User user = getUserUseCase.execute(id);

        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User with ID " + id + " does not exist.");
        }
        return user;
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

        User updatedUser = new User(id, dni, name, surname, email, age, telephone);
        UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(new UserDataRepository(UserFileLocalDataSource.getInstance()));
        updateUserUseCase.execute(updatedUser);
    }
}
