package com.addressbook.model;

/**
 * AddressBook - Domain Model
 *
 * Represents an Address Book contact in the application domain.
 * The Controller receives an AddressBookDTO from the client; the Service
 * layer converts it into this Model for internal processing.
 *
 * At this stage the Model is a plain Java class without JPA annotations.
 * @Entity, @Id, and @GeneratedValue will be added when the Repository
 * and MySQL persistence layer are introduced in a later use case.
 *
 * Fields:
 *   id      - Unique identifier for the contact (assigned by the service)
 *   name    - Full name of the contact
 *   phone   - Phone number
 *   email   - Email address
 *   address - Physical address
 */
public class AddressBook {

    private long   id;
    private String name;
    private String phone;
    private String email;
    private String address;

    // Default constructor
    public AddressBook() {}

    // Parameterized constructor
    public AddressBook(long id, String name, String phone, String email, String address) {
        this.id      = id;
        this.name    = name;
        this.phone   = phone;
        this.email   = email;
        this.address = address;
    }

    // Getter for id
    public long getId() { return id; }

    // Setter for id
    public void setId(long id) { this.id = id; }

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