package io.github.noseary.hibernatedemo.controller;

import io.github.noseary.hibernatedemo.dto.CreateUserDto;
import io.github.noseary.hibernatedemo.entity.Customer;
import io.github.noseary.hibernatedemo.entity.User;
import io.github.noseary.hibernatedemo.service.UserService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<User> getUsers(@RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "size", required = false) Integer size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/users/{id}/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getUserCustomer(@PathVariable("id") Long id) {
        return userService.getAllCustomers(id);
    }

    @GetMapping(value = "/users/{id}/info/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getUserCustomerById(@PathVariable("id") Long id,
        @PathVariable("customerId") Long customerId) {
        return userService.getAllCustomersByUser(id, customerId);
    }

    @PutMapping(value = "/users/{id}/info/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer createOrUpdateUserCustomer(@PathVariable("id") Long id,
        @PathVariable("customerId") Long customerId) {
        return userService.getAllCustomersByUser(id, customerId);
    }

    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createOrUpdateUser(@RequestBody CreateUserDto createUserDto) {
        User createdUser = userService.createUser(createUserDto);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdUser.getUserId())
            .toUri();
        return ResponseEntity.created(location).build();
    }

}
