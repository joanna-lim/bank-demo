package dev.interns.BankDemo.service;

import dev.interns.BankDemo.entity.Customer;
import dev.interns.BankDemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BankAccountService bankAccountService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BankAccountService bankAccountService) {
        this.customerRepository = customerRepository;
        this.bankAccountService = bankAccountService;
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

    @Transactional
    public Customer signUp(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        // Create a bank account for the customer
        bankAccountService.createBankAccount(0.0, savedCustomer.getId()); 

        return savedCustomer;
    }

    // honestly not important - this function can be deleted
    public Optional<Customer> singleCustomer(String ssn) {
        return customerRepository.findCustomerBySsn(ssn);
    }
}

