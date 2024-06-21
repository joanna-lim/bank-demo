package dev.interns.BankDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> allCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> singleCustomer(String SSN) {
        return customerRepository.findCustomerBySSN(SSN);
    }
}
