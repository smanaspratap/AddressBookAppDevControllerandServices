package com.addressbook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * AddressBookController - Section 1 UC2
 *
 * REST Controller for the Address Book Application.
 * Demonstrates all five HTTP methods: GET, GET by ID, POST, PUT, DELETE.
 * Uses ResponseEntity to return proper HTTP status codes with JSON responses.
 * At this stage data is NOT persisted - the focus is on REST connectivity
 * and confirming that data is transmitted correctly in REST calls via CURL.
 *
 * Base URL: /addressbook/contacts
 *
 * CURL Commands:
 *   GET all   : curl -X GET http://localhost:8080/addressbook/contacts
 *   GET by ID : curl -X GET http://localhost:8080/addressbook/contacts/1
 *   POST      : curl -X POST http://localhost:8080/addressbook/contacts -H "Content-Type: application/json" -d "{\"name\":\"John\",\"phone\":\"9999999999\",\"email\":\"john@test.com\",\"address\":\"Pune\"}"
 *   PUT       : curl -X PUT http://localhost:8080/addressbook/contacts/1 -H "Content-Type: application/json" -d "{\"name\":\"Jane\",\"phone\":\"8888888888\",\"email\":\"jane@test.com\",\"address\":\"Mumbai\"}"
 *   DELETE    : curl -X DELETE http://localhost:8080/addressbook/contacts/1
 */
@RestController
@RequestMapping("/addressbook/contacts")
public class AddressBookController {

    // GET all contacts - returns sample message confirming REST is working
    @GetMapping
    public ResponseEntity<Map<String, String>> getAllContacts() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "GET All Contacts - REST connectivity established");
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET contact by ID - echoes back the ID received in the path variable
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, String>> getContactById(@PathVariable int id) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "GET Contact ID: " + id + " - REST connectivity established");
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // POST - echoes back the request body to confirm data transmission
    @PostMapping
    public ResponseEntity<Map<String, String>> addContact(@RequestBody Map<String, String> body) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "POST Contact received - REST connectivity established");
        response.put("name",  body.getOrDefault("name",  "N/A"));
        response.put("phone", body.getOrDefault("phone", "N/A"));
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // PUT - confirms update request received for the given ID
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateContact(@PathVariable int id,
                                                             @RequestBody Map<String, String> body) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "PUT Update ID: " + id + " - REST connectivity established");
        response.put("name", body.getOrDefault("name", "N/A"));
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // DELETE - confirms delete request received for the given ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteContact(@PathVariable int id) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "DELETE Contact ID: " + id + " - REST connectivity established");
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}