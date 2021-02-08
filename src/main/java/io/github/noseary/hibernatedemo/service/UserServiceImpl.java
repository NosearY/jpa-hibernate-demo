package io.github.noseary.hibernatedemo.service;

import io.github.noseary.hibernatedemo.dao.IGenericDao;
import io.github.noseary.hibernatedemo.dto.CreateUserDto;
import io.github.noseary.hibernatedemo.entity.Customer;
import io.github.noseary.hibernatedemo.entity.User;
import io.github.noseary.hibernatedemo.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    IGenericDao<User, Long> userDao;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getAllUsers(Integer page, Integer size ) {
        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page, size) ;
            return userDao.findAllPaginated(pageable);
        } else {
            return new PageImpl<User>(userDao.findAll());
        }
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public List<Customer> getAllCustomers(Long userId) {
        return userDao.findOne(userId).getCustomers();
    }

    @Override
    public Customer getAllCustomersByUser(Long userId, Long customerId) {
        return userDao.findOne(userId).getCustomers().stream()
            .filter(customer -> customerId.equals(customerId)).findFirst().orElse(null);
    }

    @Override
    public User createUser(CreateUserDto createUserDto) {

        User user = userDao.findByName("username", createUserDto.getUsername());

        if (user == null) {
            user = new User();
            user.setUsername(createUserDto.getUsername());
            user.setPasswordHash(createUserDto.getPasswordHash());
            Customer customer = new Customer();
            customer.setCustomerName(createUserDto.getCustomerName());
            user.addCustomerIfNotExist(customer);
            userDao.create(user);
        } else {
            user.setPasswordHash(createUserDto.getPasswordHash());
            Customer customer = new Customer();
            customer.setCustomerName(createUserDto.getCustomerName());
            user.addCustomerIfNotExist(customer);
            userDao.update(user);
        }
        return user;
    }

    @Override
    public User createUserCustomer(CreateUserDto createUserDto) {
        return null;
    }
}
