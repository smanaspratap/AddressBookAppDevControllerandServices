package com.addressbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * AddressBookAppApplication
 *
 * Entry point for the Spring Boot Address Book Application.
 * This application exposes REST APIs to manage address book contacts.
 * It follows a layered architecture: Controller, Service, DTO, and Model.
 * Future layers will add JPA Repository and MySQL persistence.
 */
@SpringBootApplication
public class AddressBookAppApplication {

    // Main method - launches the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(AddressBookAppApplication.class, args);
    }
}