package com.iesam.digitallibrary.features.domain;

public class User {

    public final String dni;
    public final String id;
    public final String name;
    public final String surname;
    public final String email;
    public final String age;
    public final String telephone;

    public User(String dni, String id, String name, String surname, String email, String age, String telephone) {
        this.dni = dni;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.telephone = telephone;
    }
}
