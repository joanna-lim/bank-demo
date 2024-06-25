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

    // honestly not important - this function can be deleted
    public Optional<Customer> singleCustomer(String ssn) {
        return customerRepository.findCustomerBySsn(ssn);
    }
}

