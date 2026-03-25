package com.addressbook.controller;

import com.addressbook.dto.AddressBookDTO;
import com.addressbook.model.AddressBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * AddressBookController - Section 2 UC1
 *
 * REST Controller updated to accept AddressBookDTO as the request body
 * and return AddressBook Model objects wrapped in ResponseEntity.
 * At this stage the Controller creates the Model directly from the DTO.
 * Managing the Model is the responsibility of the Service layer (next UC).
 *
 * Base URL: /addressbook/contacts
 *
 * CURL Commands:
 *   GET all   : curl -X GET http://localhost:8080/addressbook/contacts
 *   GET by ID : curl -X GET http://localhost:8080/addressbook/contacts/1
 *   POST      : curl -X POST http://localhost:8080/addressbook/contacts -H "Content-Type: application/json" -d "{\"name\":\"John Doe\",\"phone\":\"9876543210\",\"email\":\"john@example.com\",\"address\":\"123 Main St\"}"
 *   PUT       : curl -X PUT http://localhost:8080/addressbook/contacts/1 -H "Content-Type: application/json" -d "{\"name\":\"Jane Doe\",\"phone\":\"9876543211\",\"email\":\"jane@example.com\",\"address\":\"456 Park Ave\"}"
 *   DELETE    : curl -X DELETE http://localhost:8080/addressbook/contacts/1
 */
@RestController
@RequestMapping("/addressbook/contacts")
public class AddressBookController {

    // GET all - returns a sample list to confirm DTO and Model are wired correctly
    @GetMapping
    public ResponseEntity<List<AddressBook>> getAllContacts() {
        List<AddressBook> contacts = new ArrayList<>();
        contacts.add(new AddressBook(1L, "Sample Contact", "9999999999",
                "sample@test.com", "Sample Address"));
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    // GET by ID - constructs a Model using the ID from the path variable
    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getContactById(@PathVariable long id) {
        AddressBook contact = new AddressBook(id, "Contact " + id, "9000000000",
                "contact" + id + "@test.com", "Address " + id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    // POST - accepts AddressBookDTO, creates AddressBook Model and returns it
    @PostMapping
    public ResponseEntity<AddressBook> addContact(@RequestBody AddressBookDTO dto) {
        AddressBook contact = new AddressBook(1L, dto.getName(), dto.getPhone(),
                dto.getEmail(), dto.getAddress());
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    // PUT - accepts DTO and ID, creates updated AddressBook Model and returns it
    @PutMapping("/{id}")
    public ResponseEntity<AddressBook> updateContact(@PathVariable long id,
                                                     @RequestBody AddressBookDTO dto) {
        AddressBook contact = new AddressBook(id, dto.getName(), dto.getPhone(),
                dto.getEmail(), dto.getAddress());
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    // DELETE - returns confirmation string for the deleted contact ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable long id) {
        return new ResponseEntity<>("Contact with ID " + id + " deleted successfully", HttpStatus.OK);
    }
}