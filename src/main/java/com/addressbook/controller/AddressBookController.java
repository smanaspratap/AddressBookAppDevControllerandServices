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
 * AddressBookController - Section 2 UC2
 *
 * REST Controller now delegates ALL business logic to AddressBookService.
 * @Autowired annotation is used for Dependency Injection of the service bean.
 * The Controller is now a thin HTTP layer only - no business logic here.
 *
 * Separation of Concerns:
 *   Controller -> HTTP request/response mapping only
 *   Service    -> all business logic and Model management
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

    // Injecting AddressBookService via Spring Dependency Injection (@Autowired)
    @Autowired
    private AddressBookService addressBookService;

    // GET all contacts - delegates to service layer
    @GetMapping
    public ResponseEntity<List<AddressBook>> getAllContacts() {
        return new ResponseEntity<>(addressBookService.getAllContacts(), HttpStatus.OK);
    }

    // GET contact by ID - delegates to service layer
    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getContactById(@PathVariable long id) {
        return new ResponseEntity<>(addressBookService.getContactById(id), HttpStatus.OK);
    }

    // POST - delegates DTO to service which creates and returns the new Model
    @PostMapping
    public ResponseEntity<AddressBook> addContact(@RequestBody AddressBookDTO dto) {
        return new ResponseEntity<>(addressBookService.addContact(dto), HttpStatus.CREATED);
    }

    // PUT - delegates ID and DTO to service which updates and returns the Model
    @PutMapping("/{id}")
    public ResponseEntity<AddressBook> updateContact(@PathVariable long id,
                                                     @RequestBody AddressBookDTO dto) {
        return new ResponseEntity<>(addressBookService.updateContact(id, dto), HttpStatus.OK);
    }

    // DELETE - delegates to service which removes contact and returns status message
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable long id) {
        return new ResponseEntity<>(addressBookService.deleteContact(id), HttpStatus.OK);
    }
}