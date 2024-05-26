package com.iesam.digitallibrary.features.user.presentation;

import com.iesam.digitallibrary.features.user.domain.User;
import com.iesam.digitallibrary.features.user.domain.UserRepository;

import java.util.List;
import java.util.Scanner;

public class UserPresentation {

    private final Scanner scanner = new Scanner(System.in);
    private final UserRepository userRepository;

    public UserPresentation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void showUserMenu() {
        int option;
        do {
            System.out.println("\nMenú de Gestión de Usuarios:");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Eliminar Usuario");
            System.out.println("3. Mostrar Todos los Usuarios");
            System.out.println("4. Mostrar Usuario por ID");
            System.out.println("5. Actualizar un Usuario");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Ingrese su opción: \n");
            option = scanner.nextInt();
            switch (option) {
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
                    break;
                case 6:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        } while (option != 6);
    }

    private void createUser() {
        System.out.println("DNI: ");
        scanner.nextLine(); // Limpiar el buffer
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
        userRepository.createUser(newUser);
    }

    private void getUsers() {
        List<User> users = userRepository.getUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    private void getUser() {
        System.out.println("User ID to list: ");
        scanner.nextLine(); // Limpiar el buffer
        String id = scanner.nextLine();

        User user = userRepository.getUser(id);

        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User with ID " + id + " does not exist.");
        }
    }

    private void deleteUser() {
        System.out.println("User ID to delete: ");
        scanner.nextLine(); // Limpiar el buffer
        String id = scanner.nextLine();
        userRepository.deleteUser(id);
    }

    private void updateUser() {
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
        userRepository.updateUser(updatedUser);
    }
}
