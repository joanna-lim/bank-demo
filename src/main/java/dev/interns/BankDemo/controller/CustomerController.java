package dev.interns.BankDemo.controller;

import dev.interns.BankDemo.entity.Customer;
import dev.interns.BankDemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestParam String username, @RequestParam String password) {
        try {
            Optional<Customer> customerOpt = customerService.login(username, password);
            
            if (customerOpt.isPresent()) {
                return ResponseEntity.ok(customerOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Customer> signUp(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.signUp(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
}

