package dev.interns.BankDemo.service;

import dev.interns.BankDemo.entity.Customer;
import dev.interns.BankDemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> allCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    public Optional<Customer> login(String username, String password) {
        Optional<Customer> customerOpt = customerRepository.findByUsername(username);
        if (customerOpt.isPresent() && customerOpt.get().getPassword().equals(password)) {
            return customerOpt;
        }
        return Optional.empty();
    }

    // honestly not important - this function can be deleted
    public Optional<Customer> singleCustomer(String ssn) {
        return customerRepository.findCustomerBySsn(ssn);
    }
}

