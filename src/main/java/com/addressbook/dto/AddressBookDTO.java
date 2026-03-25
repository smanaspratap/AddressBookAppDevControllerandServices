package com.addressbook.dto;

/**
 * AddressBookDTO - Data Transfer Object
 *
 * Represents the data shape sent by the client (frontend / CURL) in REST requests.
 * This DTO decouples the API contract from the internal domain Model.
 * Getters and setters are written explicitly for full clarity at this stage.
 *
 * Fields:
 *   name    - Full name of the contact
 *   phone   - Phone number of the contact
 *   email   - Email address of the contact
 *   address - Physical address of the contact
 */
public class AddressBookDTO {

    private String name;
    private String phone;
    private String email;
    private String address;

    // Default constructor required for JSON deserialization by Jackson
    public AddressBookDTO() {}

    // Parameterized constructor
    public AddressBookDTO(String name, String phone, String email, String address) {
        this.name    = name;
        this.phone   = phone;
        this.email   = email;
        this.address = address;
    }

    // Getter for name
    public String getName() { return name; }

    // Setter for name
    public void setName(String name) { this.name = name; }

    // Getter for phone
    public String getPhone() { return phone; }

    // Setter for phone
    public void setPhone(String phone) { this.phone = phone; }

    // Getter for email
    public String getEmail() { return email; }

    // Setter for email
    public void setEmail(String email) { this.email = email; }

    // Getter for address
    public String getAddress() { return address; }

    // Setter for address
    public void setAddress(String address) { this.address = address; }
}