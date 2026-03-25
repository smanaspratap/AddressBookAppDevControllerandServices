package com.addressbook.controller;

import com.addressbook.dto.AddressBookDTO;
import com.addressbook.model.AddressBook;
import com.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AddressBookController - Section 2 UC3
 *
 * REST Controller for the Address Book Application (final UC5 version).
 * The Service Layer now stores data in an in-memory ArrayList, so CRUD operations
 * reflect actual state changes for the duration of the application runtime.
 * Controller now handles null responses from service by returning HTTP 404 Not Found.
 *
 * Base URL: /addressbook/contacts
 *
 * CURL Test Sequence (run in order):
 *
 *   Step 1 - POST contact 1:
 *     curl -X POST http://localhost:8080/addressbook/contacts -H "Content-Type: application/json" -d "{\"name\":\"John Doe\",\"phone\":\"9876543210\",\"email\":\"john@example.com\",\"address\":\"123 Main St\"}"
 *
 *   Step 2 - POST contact 2:
 *     curl -X POST http://localhost:8080/addressbook/contacts -H "Content-Type: application/json" -d "{\"name\":\"Jane Smith\",\"phone\":\"9876543211\",\"email\":\"jane@example.com\",\"address\":\"456 Park Ave\"}"
 *
 *   Step 3 - GET all (should show 2 contacts):
 *     curl -X GET http://localhost:8080/addressbook/contacts
 *
 *   Step 4 - GET by ID 1:
 *     curl -X GET http://localhost:8080/addressbook/contacts/1
 *
 *   Step 5 - PUT update ID 1:
 *     curl -X PUT http://localhost:8080/addressbook/contacts/1 -H "Content-Type: application/json" -d "{\"name\":\"John Updated\",\"phone\":\"1111111111\",\"email\":\"updated@example.com\",\"address\":\"789 New Rd\"}"
 *
 *   Step 6 - DELETE ID 1:
 *     curl -X DELETE http://localhost:8080/addressbook/contacts/1
 *
 *   Step 7 - GET all (should show only contact 2):
 *     curl -X GET http://localhost:8080/addressbook/contacts
 */
@RestController
@RequestMapping("/addressbook/contacts")
public class AddressBookController {

    // Injecting AddressBookService via Spring Dependency Injection (@Autowired)
    @Autowired
    private AddressBookService addressBookService;

    // GET all contacts - retrieves full list from in-memory store via service
    @GetMapping
    public ResponseEntity<List<AddressBook>> getAllContacts() {
        return new ResponseEntity<>(addressBookService.getAllContacts(), HttpStatus.OK);
    }

    // GET contact by ID - returns 404 Not Found if contact does not exist in list
    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getContactById(@PathVariable long id) {
        AddressBook contact = addressBookService.getContactById(id);
        if (contact == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    // POST - adds new contact to in-memory list via service; returns created contact
    @PostMapping
    public ResponseEntity<AddressBook> addContact(@RequestBody AddressBookDTO dto) {
        return new ResponseEntity<>(addressBookService.addContact(dto), HttpStatus.CREATED);
    }

    // PUT - updates existing contact by ID; returns 404 if ID not found in list
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable long id,
                                           @RequestBody AddressBookDTO dto) {
        AddressBook updated = addressBookService.updateContact(id, dto);
        if (updated == null) {
            return new ResponseEntity<>("Contact with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // DELETE - removes contact from in-memory list by ID via service
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable long id) {
        return new ResponseEntity<>(addressBookService.deleteContact(id), HttpStatus.OK);
    }
}