package dev.interns.BankDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<List<Customer>>(customerService.allCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{SSN}")
    public ResponseEntity<Optional<Customer>> getSingleCustomer(@PathVariable String SSN) {
        return new ResponseEntity<Optional<Customer>>(customerService.singleCustomer(SSN), HttpStatus.OK);
    }
}
