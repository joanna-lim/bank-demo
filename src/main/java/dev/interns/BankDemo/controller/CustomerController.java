package dev.interns.BankDemo.controller;

import dev.interns.BankDemo.entity.Customer;
import dev.interns.BankDemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.allCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{ssn}")
    public ResponseEntity<Optional<Customer>> getSingleCustomer(@PathVariable String ssn) {
        Optional<Customer> customer = customerService.singleCustomer(ssn);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}

