package com.elderj.contactsbook;

public class Person {

    public final int id;
    public final String firstName;
    public final String lastName;
    public final String email;
    public final String phone;

    public Person(int id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

}
