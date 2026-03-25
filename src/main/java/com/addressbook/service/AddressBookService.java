package com.addressbook.service;

import com.addressbook.dto.AddressBookDTO;
import com.addressbook.model.AddressBook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * AddressBookService - Service Layer
 *
 * Manages AddressBook contact data on behalf of the Controller.
 * Annotated with @Service so Spring registers it as a managed singleton bean
 * available for Dependency Injection via @Autowired in the Controller.
 *
 * Data is stored in an in-memory ArrayList for the duration of runtime.
 * Note: data is lost on application restart.
 * Database persistence via Spring Data JPA will be added in a later use case.
 *
 * Supported Operations:
 *   getAllContacts()        - Returns all stored contacts
 *   getContactById(id)     - Returns the contact matching the given ID
 *   addContact(dto)        - Creates, stores, and returns a new contact
 *   updateContact(id, dto) - Updates and returns an existing contact
 *   deleteContact(id)      - Removes a contact and returns a status message
 */
@Service
public class AddressBookService {

    // In-memory storage list for AddressBook contacts
    private final List<AddressBook> contactList = new ArrayList<>();

    // Thread-safe auto-incrementing ID counter for new contacts
    private final AtomicLong idCounter = new AtomicLong(1);

    // Returns all contacts currently stored in the in-memory list
    public List<AddressBook> getAllContacts() {
        return contactList;
    }

    // Searches the list and returns the contact with the given ID; null if not found
    public AddressBook getContactById(long id) {
        Optional<AddressBook> contact = contactList.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        return contact.orElse(null);
    }

    // Creates a new AddressBook contact from DTO, assigns auto-incremented ID, stores it
    public AddressBook addContact(AddressBookDTO dto) {
        AddressBook contact = new AddressBook(
                idCounter.getAndIncrement(),
                dto.getName(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getAddress()
        );
        contactList.add(contact);
        return contact;
    }

    // Finds contact by ID in list, updates all fields from DTO, returns updated contact
    public AddressBook updateContact(long id, AddressBookDTO dto) {
        for (AddressBook contact : contactList) {
            if (contact.getId() == id) {
                contact.setName(dto.getName());
                contact.setPhone(dto.getPhone());
                contact.setEmail(dto.getEmail());
                contact.setAddress(dto.getAddress());
                return contact;
            }
        }
        return null;
    }

    // Removes the contact with the given ID from the list; returns a status message
    public String deleteContact(long id) {
        boolean removed = contactList.removeIf(c -> c.getId() == id);
        return removed
                ? "Contact with ID " + id + " deleted successfully"
                : "Contact with ID " + id + " not found";
    }
}