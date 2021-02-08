package io.github.noseary.hibernatedemo.service;

import io.github.noseary.hibernatedemo.dto.CreateUserDto;
import io.github.noseary.hibernatedemo.entity.Customer;
import io.github.noseary.hibernatedemo.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<User> getAllUsers(Integer page, Integer size);

    User getUserById(Long id);

    List<Customer> getAllCustomers(Long userId);

    Customer getAllCustomersByUser(Long userId, Long customerId);

    User createUser(CreateUserDto createUserDto);

    User createUserCustomer(CreateUserDto createUserDto);
}
