package com.iesam.digitallibrary.features.user.domain;

public class User {

    public final String id;
    public final String dni;
    public final String name;
    public final String surname;
    public final String email;
    public final String age;
    public final String telephone;

    public User(String id, String dni, String name, String surname, String email, String age, String telephone) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
